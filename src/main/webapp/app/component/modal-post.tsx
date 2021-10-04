import React, {useState, useEffect} from 'react';
import {useAppDispatch} from 'app/config/store';
import { Modal} from 'reactstrap';
// import Button from '@restart/ui/esm/Button';
import axios from 'axios';
import Button from '@restart/ui/esm/Button';


const ModalPosts = (props) => {
  const {isModal, handlePostModal, name, handlPostContent} = props
  const dispatch = useAppDispatch();

  const [imageUpload, setImageUpload] = useState("")
  const [videoUpload, setVideoUpload] = useState("")

  const [contents, setContent] = useState({
    content: "",
    photoContent: "",
    videoContent: ""
  })

  const handleOpenModal = () => {
    handlePostModal()
  }

  const handleUploadFile = async (e) => {
    console.log(e.target.files[0]);
    const formData = new FormData();

    formData.append('imageData', e.target.files[0])
    formData.append('desiredName', "meo-meo")
   await axios.post('/api/o/images/upload',
    formData
    ,
    {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }
  ).then((res) => {
    console.log('SUCCESS!!', res.data.imagesSaved[0].url);
    setImageUpload(res.data.imagesSaved[0].url)
    // createUploadFile(res.data.imagesSaved[0].url)
    setContent({
      ...contents,
      photoContent: res.data.imagesSaved[0].url
    })
  })
  .catch(function(){
    console.log('FAILURE!!');
  });
  }

  const handleUploadFileVideo = async (e) => {
    // console.log(e.target.files[0])
    const formData = new FormData();

    formData.append('videoData', e.target.files[0])
    // formData.append('desiredName', "meo-meo")
   await axios.post('/api/o/videos/upload',
    formData
    ,
    {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }
  ).then((res) => {
    console.log('SUCCESS!!', res);
    setVideoUpload(res.data.videoSaved[0].url)
    // createVideoUpload(res.data.videoSaved[0].url)
    setContent({
      ...contents,
      videoContent: res.data.videoSaved[0].url
    })

  })
  .catch(function(){
    console.log('FAILURE!!');
  });
  }  

  const handleOnchangeValue = (e) => {
    // console.log(e.target.value)
      // createTextContent(e.target.value)
      setContent({
        ...contents,
        content: e.target.value
      })
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    const contentJson = JSON.stringify(contents)
    handlPostContent(contentJson)
    handleOpenModal()
  }

  // useEffect(() => {
  //   console.log(contents);
  //   return () => {
      
  //   }
  // }, [contents])

  return (
    <Modal isOpen={isModal} toggle={handleOpenModal}>
      <form className="form__post" onSubmit={handleSubmit}>
        <div className="form__post-wrap">
          <div className="modal__header">
            <div className="modal__header-top">
              Tạo bài viết
            </div>
            <div className="modal__header-bottom">
              <div className="avatar__photo">
                <img src='assets/images/user.png' alt='icon' className='shadow-sm rounded-circle avatar__img' />
              </div>
              <div className="name__wrap">
                <p className='name__text'>{name}</p>
                <div className="status">
                  <p className="status-text">Công khai</p>
                </div>
              </div>
            </div>

            <div className="modal__body">
              <div className="modal__body-content">
                <textarea 
                  name='message' 
                  className='bor-0 w-100 rounded-xxl p-2 font-xssss text-grey-500 fw-500 border-light-md theme-dark-bg form__text'
                  cols={30}
                  rows={10} 
                  placeholder="What's on your mind?" 
                  onChange={handleOnchangeValue}
                />
              </div>
              <div className="wrap__photo">
                <div className="wrap__photo-inner" >
                  {imageUpload === "" ? (
                    <div>
                      <div className="add__icon">
                    +
                    </div>
                    <div className="add__text">
                    Thêm ảnh/Video
                    </div>
                    </div>
                  )
                  : (<div>
                    <img src={imageUpload} alt="upload" className="add__img" />
                    <img src={imageUpload} alt="upload" className="add__img" />
                  </div>)}
                  <input type="file" name="video" className="form-contol uploader-input" onChange={handleUploadFile}  />
                  
                </div>
              </div>
            </div>
          </div>
        </div>

        <Button color="primary" type="submit">Đăng</Button>
      </form>
    </Modal>
  );
};

export default ModalPosts;
