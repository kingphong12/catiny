import Websocket from "app/config/Websocket";
import React, {useCallback, useEffect, useRef, useState} from "react";
import {useAppDispatch, useAppSelector} from "app/config/store";
import {initVideoLivestream} from "app/modules/livestream/livestream.reducer";
import {RouteComponentProps} from "react-router-dom";

let sourceBuffer = null;
let mediaSource = new MediaSource();
let currentVideoLivestream = null;
const sequenceDataQueue = new Map();
const Livestream = (props: RouteComponentProps<{ id: string }>) =>
{
  const masterUser = useAppSelector(state => state.authentication.masterUser);
  const websocket = new Websocket();
  const dispatch = useAppDispatch();
  const id = props.match.params.id;
  currentVideoLivestream = useAppSelector(state => state.livestream.currentVideoLivestream);

  const webcamRef = useRef(null);
  const videoRef = useRef(null);
  const mediaRecorderRef = useRef(null);
  const [capturing, setCapturing] = useState(false);


  const videoConstraints = {
    video: {
      width: {
        // max: 1920,
        ideal: 960,
        // ideal: 320,
        // min:640
      },
      height: {
        // max: 1080,
        ideal: 540,
        // ideal: 180,
        // min:360
      },
      facingMode: {exact: 'user'},
      frameRate: 30,
    },
    audio: true,
  };

  const displayConstraints = {
    video: {
      width: {
        max: 1920,

        ideal: 960,
      },
      height: {
        max: 1080,

        ideal: 540,
      },
      frameRate: 30,
    },
    audio: true,
  };

  // useEffect(() =>
  // {
  //   mediaSource = new MediaSource();
  //   mediaSource.addEventListener('sourceopen', () => (sourceBuffer = mediaSource.addSourceBuffer('video/webm;codecs=vp8')));
  //   videoRef.current.src = window.URL.createObjectURL(mediaSource);
  // }, [setCapturing]);

  const handleStartCaptureClick = useCallback(async type =>
  {
    setCapturing(true);
    mediaSource = new MediaSource();
    if (type === 'camera')
    {
      mediaRecorderRef.current = new MediaRecorder(await navigator.mediaDevices.getUserMedia(videoConstraints), {
        mimeType: 'video/webm;codecs=vp8,opus',
        audioBitsPerSecond: 128000,
        videoBitsPerSecond: 1000000,
      });
      mediaSource.addEventListener('sourceopen', () => (sourceBuffer = mediaSource.addSourceBuffer('video/webm;codecs=vp8,opus')));
    }
    else
    {
      // eslint-disable-next-line @typescript-eslint/ban-ts-comment
      // @ts-ignore
      mediaRecorderRef.current = new MediaRecorder(await navigator.mediaDevices.getDisplayMedia(displayConstraints), {
        audioBitsPerSecond: 12800,
        videoBitsPerSecond: 5000,
        mimeType: 'video/webm;codecs=vp8',
      });
      mediaSource.addEventListener('sourceopen', () => (sourceBuffer = mediaSource.addSourceBuffer('video/webm;codecs=vp8')));
    }
    webcamRef.current.srcObject = mediaRecorderRef.current.stream;
    // mediaRecorderRef.current = new MediaRecorder(webcamRef.current.stream, {mimeType: "video/webm"});
    // videoRef.current.srcObject=webcamRef.current.stream;
    mediaRecorderRef.current.ondataavailable = handleDataAvailable;
    mediaRecorderRef.current.start(1);
    videoRef.current.src = window.URL.createObjectURL(mediaSource);
  }, [webcamRef, setCapturing, mediaRecorderRef]);

  const handleDataAvailable = useCallback(({data}) =>
  {
    if (data.size > 0)
    {
      const prevSequenceDataQueue = sequenceDataQueue.get(currentVideoLivestream.info.uuid);
      const lastSequenceNumber = prevSequenceDataQueue.lastSequenceNumber + 1;
      sequenceDataQueue.set(currentVideoLivestream.info.uuid, {...prevSequenceDataQueue, lastSequenceNumber});
      const reader = new FileReader();
      reader.readAsDataURL(data);
      reader.onloadend = () => websocket.sendProducer(`/livestream.data/${currentVideoLivestream.info.uuid}`,
        {
          data: reader.result,
          sequenceNumber: lastSequenceNumber,
          currentTime: webcamRef.current.currentTime,
        });

    }
  }, []);

  useEffect(() =>
  {
    websocket.connect("/websocket/main");
    if (id && masterUser.uuid)
    {
      mediaSource = new MediaSource();
      mediaSource.addEventListener('sourceopen', () => (sourceBuffer = mediaSource.addSourceBuffer('video/webm;codecs=vp8,opus')));
      videoRef.current.src = window.URL.createObjectURL(mediaSource);

      websocket.subscribeUserConsumer(`/livestream.data/${id}`, processData);
    }
    if (masterUser.uuid && currentVideoLivestream && currentVideoLivestream.info.uuid)
    {
      sequenceDataQueue.set(currentVideoLivestream.info.uuid,
        {
          lastSequenceNumber: 0 as number,
          dataQueue: new Map(),
          lastSequenceNumberAdded: 0 as number,
        });
      websocket.subscribeUserConsumer(`/livestream.data/${currentVideoLivestream.info.uuid}`, processData);
    }
  }, [masterUser.uuid, currentVideoLivestream]);

  const processData = useCallback(async data =>
  {
    const result = JSON.parse(data.body);
    let videoBase64 = result.data.split(',');
    videoBase64 = videoBase64[videoBase64.length - 1];
    const videoData = Uint8Array.from(atob(videoBase64), c => c.charCodeAt(0));
    // sourceBuffer.appendBuffer(new Uint8Array(videoData));

    const sequenceNumber = result.sequenceNumber;
    const livestreamId = result.id;
    const sequence = sequenceDataQueue.get(livestreamId);
    let lastSequenceNumberAdded = sequence.lastSequenceNumberAdded;

    const prevDataQueue = sequence.dataQueue;
    prevDataQueue.set(sequenceNumber, videoData);

    while (prevDataQueue.has(++lastSequenceNumberAdded))
    {
      const dataTemp = prevDataQueue.get(lastSequenceNumberAdded);
      if (sourceBuffer.updating)
        break;
      sourceBuffer.appendBuffer(new Uint8Array(dataTemp));
      prevDataQueue.delete(lastSequenceNumberAdded);
    }
    sequenceDataQueue.set(livestreamId,
      {
        ...sequence,
        dataQueue: prevDataQueue,
        lastSequenceNumberAdded: lastSequenceNumberAdded - 1,
      });

    // todo điều chỉnh độ trễ
    // if (result.currentTime-videoRef.current.currentTime>0.5)
    // {
    //   console.log(result.currentTime-videoRef.current.currentTime);
    //   videoRef.current.currentTime=result.currentTime-0.001
    // }
  }, [sequenceDataQueue]);


  const phone = () =>
  {
    const startCapture = () =>
    {
      dispatch(initVideoLivestream());
      handleStartCaptureClick("camera");
    }
    const stopCapture = () =>
    {
      setCapturing(false);
      mediaRecorderRef.current.stop();
      mediaRecorderRef.current = null;
      videoRef.current.src = "";
      sourceBuffer = null;
      sequenceDataQueue.set(currentVideoLivestream.info.uuid,
        {
          lastSequenceNumber: 0 as number,
          dataQueue: new Map(),
          lastSequenceNumberAdded: 0 as number,
        });
    }

    if (!capturing)
      return <div className='btn-round-xxl bg-success z-index-1' onClick={startCapture}>
        <i className='feather-phone-call text-white font-md' /></div>
    else
      return <>
        <div className='btn-round-xl d-md-inline-block d-none bg-blur m-3 me-0 z-index-1'>
          <i className='feather-grid text-white font-md' /></div>
        <div className='btn-round-xl d-md-inline-block d-none bg-blur m-3 z-index-1'>
          <i className='feather-mic-off text-white font-md' /></div>
        <div className='btn-round-xxl bg-danger z-index-1' onClick={stopCapture}>
          <i className='feather-phone-off text-white font-md' /></div>
        <div className='btn-round-xl d-md-inline-block d-none bg-blur m-3 z-index-1'>
          <i className='ti-video-camera text-white font-md' /></div>
        <div className='btn-round-xl d-md-inline-block d-none bg-blur m-3 ms-0 z-index-1'>
          <i className='ti-settings text-white font-md' /></div>
        <span
          className='p-2 bg-blur z-index-1 text-white fw-700 font-xssss rounded-3 right-15 position-absolute mb-4 bottom-0'>44:00</span>
        <span
          className='live-tag position-absolute left-15 mt-2 bottom-0 mb-4 bg-danger p-2 z-index-1 rounded-3 text-white font-xsssss text-uppersace fw-700 ls-3'>LIVE</span>
      </>
  }

  return (
    <div className='main-content right-chat-active'>
      <div className='middle-sidebar-bottom'>
        <div className='middle-sidebar-left pe-0' style={{maxWidth: "100%"}}>
          <div className='row'>
            <div className='col-xl-8 col-xxl-9 col-lg-8'>
              <div className='card border-0 mb-0 rounded-3 overflow-hidden chat-wrapper bg-image-center bg-image-cover'
                   style={{backgroundImage: `url("https://via.placeholder.com/975x700.png")`}}>
                <div className='card-body position-absolute mt-0 ms-0 left-0'>
                  {capturing ?
                    <video className='w150 h200 rounded-3 position-relative z-index-1 shadow-xss'
                           autoPlay playsInline muted preload={'none'} ref={webcamRef} /> : <div />}
                </div>
                <div className='card-body text-center p-2 position-absolute w-100 bottom-0 bg-gradiant-bottom'>
                  {phone()}
                </div>
                <div className='position-absolute'>
                  <video className='card border-0 mb-0 rounded-3 overflow-hidden chat-wrapper bg-image-center bg-image-cover'
                         autoPlay controls playsInline muted preload={'none'} ref={videoRef} />
                </div>
              </div>
            </div>

            <div className='col-xl-4 col-xxl-3 col-lg-4 pe-0 ps-0'>
              <div className='card w-100 d-block chat-body p-0 border-0 shadow-xss rounded-3 mb-3 position-relative'>
                <div className='messages-content chat-wrapper scroll-bar p-3'>
                  <div className='message-item'>
                    <div className='message-user'>
                      <figure className='avatar'>
                        <img src='assets/images/user.png' alt='avater' />
                      </figure>
                      <div>
                        <h5 className='font-xssss mt-2'>Byrom Guittet</h5>
                        <div className='time'>01:35 PM</div>
                      </div>
                    </div>
                    <div className='message-wrap shadow-none'>I'm fine, how are you</div>
                  </div>

                  <div className='message-item'>
                    <div className='message-user'>
                      <figure className='avatar'>
                        <img src='assets/images/user.png' alt='avater' />
                      </figure>
                      <div>
                        <h5 className='font-xssss mt-2'>Byrom Guittet</h5>
                        <div className='time'>01:35 PM<i className='ti-double-check text-info' /></div>
                      </div>
                    </div>
                    <div className='message-wrap shadow-none'>I want those files for you. I want you to send 1 PDF and 1 image file.</div>
                  </div>

                  <div className='message-item'>
                    <div className='message-user'>
                      <figure className='avatar'>
                        <img src='assets/images/user.png' alt='avater' />
                      </figure>
                      <div>
                        <h5 className='font-xssss mt-2'>Byrom Guittet</h5>
                        <div className='time'>01:35 PM</div>
                      </div>
                    </div>
                    <div className='message-wrap shadow-none'>I've found some cool photos for our travel app.</div>
                  </div>

                  <div className='message-item outgoing-message'>
                    <div className='message-user'>
                      <figure className='avatar'>
                        <img src='assets/images/user.png' alt='avater' />
                      </figure>
                      <div>
                        <h5>You</h5>
                        <div className='time'>01:35 PM<i className='ti-double-check text-info' /></div>
                      </div>
                    </div>
                    <div className='message-wrap'>Hey mate! How are things going ?</div>
                  </div>

                  <div className='message-item'>
                    <div className='message-user'>
                      <figure className='avatar'>
                        <img src='assets/images/user.png' alt='avater' />
                      </figure>
                      <div>
                        <h5 className='font-xssss mt-2'>Byrom Guittet</h5>
                        <div className='time'>01:35 PM</div>
                      </div>
                    </div>
                    <div className='message-wrap shadow-none'>I'm fine, how are you.</div>
                  </div>

                  <div className='message-item'>
                    <div className='message-user'>
                      <figure className='avatar'>
                        <img src='assets/images/user.png' alt='avater' />
                      </figure>
                      <div>
                        <h5 className='font-xssss mt-2'>Byrom Guittet</h5>
                        <div className='time'>01:35 PM<i className='ti-double-check text-info' /></div>
                      </div>
                    </div>
                    <div className='message-wrap shadow-none'>I want those files for you. I want you to send 1 PDF and 1 image file.</div>
                  </div>

                  <div className='message-item'>
                    <div className='message-user'>
                      <figure className='avatar'>
                        <img src='assets/images/user.png' alt='avater' />
                      </figure>
                      <div>
                        <h5 className='font-xssss mt-2'>Byrom Guittet</h5>
                        <div className='time'>01:35 PM</div>
                      </div>
                    </div>
                    <div className='message-wrap shadow-none'>I've found some cool photos for our travel app.</div>
                  </div>

                </div>
                <form className='chat-form position-absolute bottom-0 w-100 left-0 bg-white z-index-1 p-3 shadow-xs theme-dark-bg '>
                  <button className='bg-grey float-left'><i className='ti-microphone text-white' /></button>
                  <div className='form-group'><input type='text' placeholder='Start typing..' /></div>
                  <button className='bg-current'><i className='ti-arrow-right text-white' /></button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

}


export default Livestream;