import {useAppDispatch, useAppSelector} from "app/config/store";
import React, {useEffect, useState} from "react";
import {Modal} from "react-bootstrap";
import Websocket from "app/config/Websocket";
import {Link} from "react-router-dom";


const NotificationModal = () =>
{
  const [videoCallIncomingNotification, setVideoCallIncomingNotification] = useState(false);
  const [queryParamVideoCall, setQueryParamVideoCall] = useState("");
  const websocket = new Websocket();
  const masterUser = useAppSelector(state => state.authentication.masterUser);
  const dispatch = useAppDispatch();
  useEffect(() =>
  {
    websocket.connect("/websocket/main");
    if (masterUser.uuid)
      websocket.subscribeUserConsumer(`/notifications/video-call.incoming`, data =>
      {
        const body = JSON.parse(data.body);
        setQueryParamVideoCall(`keyConnect=${body.keyConnect}&userId=${body.userId}`);
        setVideoCallIncomingNotification(true);
      });
  }, [masterUser.uuid]);
  const videoCallIncoming = (incomingNotification, queryParam) =>
  {
    return (
      <Modal show={incomingNotification} size='sm' aria-labelledby='contained-modal-title-vcenter' centered>
        <Modal.Header closeButton onClick={() => setVideoCallIncomingNotification(false)}>
          <Modal.Title id='contained-modal-title-vcenter'>
            <h2>Cuộc gọi đến</h2>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h4>con mèo đang gọi bạn</h4>
          <p>mèo méo meo</p>
        </Modal.Body>
        <Modal.Footer className='d-flex justify-content-center'>
          <div className='btn-round-xxl bg-success z-index-1 me-5'
               onClick={() => setVideoCallIncomingNotification(false)}>
            <Link to={`/video-call?${queryParam}`}><i className='feather-phone-incoming text-white font-md' /></Link>
          </div>
          <div className='btn-round-xxl bg-danger z-index-1 ms-5'
               onClick={() => setVideoCallIncomingNotification(false)}>
            <i className='feather-phone-missed text-white font-md' />
          </div>
        </Modal.Footer>
      </Modal>
    );
  }


  return (
    <div>
      {videoCallIncoming(videoCallIncomingNotification, queryParamVideoCall)}
    </div>
  );
}

export default NotificationModal;