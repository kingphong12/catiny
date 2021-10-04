import axios from 'axios';
import React, {useState} from 'react';
// import MultipleUpload from './multiple-upload';
import {Modal} from "react-bootstrap";
import './css/imageUpload.scss'
import ModalPosts from './modal-post';

const CreatePost = () => {
  const [isOpen, setIsOpen] = useState(false);
  const toggleOpen = () => setIsOpen(!isOpen);
  const menuClass = `${isOpen ? " show" : ""}`;
  const [isModal, setIsModal] = useState(false)
  const handlePostModal = () => {
    setIsModal(!isModal)
  }

  const handlPostContent = (content) => {
    console.log(content)
     axios.post('/api/o/posts', content,
     {
       headers: {
        'Content-Type': 'application/json'
       }
     })
    .then((res) => {
    console.log('SUCCESS!!', res.data);
  })
  .catch(function(){
    console.log('FAILURE!!');
  });
  }

  return (
    <div className='card w-100 shadow-xss rounded-xxl border-0 ps-4 pt-4 pe-4 pb-3 mb-3'>
      <div className='card-body p-0'>
        <a href='/' className='font-xssss fw-600 text-grey-500 card-body p-0 d-flex align-items-center'><i
          className='btn-round-sm font-xs text-primary feather-edit-3 me-2 bg-greylight' />Create Post</a>
      </div>
      <div className='card-body p-0 mt-3 box__think'>
        <div className='avatar  mb-0 avatar__post'>
          <img src='assets/images/user.png' alt='icon' className='shadow-sm rounded-circle avatar__img' />
        </div>
        <div className="create__post" onClick={handlePostModal}>
          Bạn đang nghĩ gì?
        </div>
        {/* <textarea 
          name='message' 
          className='h100 bor-0 w-100 rounded-xxl p-2 ps-5 font-xssss text-grey-500 fw-500 border-light-md theme-dark-bg'
          cols={30}
          rows={10} 
          placeholder="What's on your mind?" /> */}
      </div>
      <div className='card-body d-flex p-0 mt-0'>
        <div className='d-flex align-items-center font-xssss fw-600 ls-1 text-grey-700 text-dark pe-4'>
          <i className='font-md text-danger feather-video me-2' />
          <span className='d-none-xs'>Live Video</span>
        </div>
          

        <div className='d-flex align-items-center font-xssss fw-600 ls-1 text-grey-700 text-dark pe-4' onClick={handlePostModal}>
          <i className='font-md text-success feather-image me-2' />
          <span className='d-none-xs'>Video/Photo</span>
          {/* <MultipleUpload label="Video/Photo" id="multiple-upload" /> */}
        </div>
        <div className='d-flex align-items-center font-xssss fw-600 ls-1 text-grey-700 text-dark pe-4'>
          <i className='font-md text-warning feather-camera me-2' />
          <span className='d-none-xs'>Feeling/Activity</span>
        </div>
        <div className={`ms-auto pointer ${menuClass}`} id='dropdownMenu4' data-bs-toggle='dropdown' aria-expanded='false'
             onClick={toggleOpen}><i className='ti-more-alt text-grey-900 btn-round-md bg-greylight font-xss' /></div>
        <div className={`dropdown-menu p-4 right-0 rounded-xxl border-0 shadow-lg ${menuClass}`} aria-labelledby='dropdownMenu4'>
          <div className='card-body p-0 d-flex'>
            <i className='feather-bookmark text-grey-500 me-3 font-lg' />
            <h4 className='fw-600 text-grey-900 font-xssss mt-0 me-4 pointer'>Save Link <span
              className='d-block font-xsssss fw-500 mt-1 lh-3 text-grey-500'>Add this to your saved items</span></h4>
          </div>
          <div className='card-body p-0 d-flex mt-2'>
            <i className='feather-alert-circle text-grey-500 me-3 font-lg' />
            <h4 className='fw-600 text-grey-900 font-xssss mt-0 me-4 pointer'>Hide Post <span
              className='d-block font-xsssss fw-500 mt-1 lh-3 text-grey-500'>Save to your saved items</span></h4>
          </div>
          <div className='card-body p-0 d-flex mt-2'>
            <i className='feather-alert-octagon text-grey-500 me-3 font-lg' />
            <h4 className='fw-600 text-grey-900 font-xssss mt-0 me-4 pointer'>Hide all from Group <span
              className='d-block font-xsssss fw-500 mt-1 lh-3 text-grey-500'>Save to your saved items</span></h4>
          </div>
          <div className='card-body p-0 d-flex mt-2'>
            <i className='feather-lock text-grey-500 me-3 font-lg' />
            <h4 className='fw-600 mb-0 text-grey-900 font-xssss mt-0 me-4 pointer'>Unfollow Group <span
              className='d-block font-xsssss fw-500 mt-1 lh-3 text-grey-500'>Save to your saved items</span></h4>
          </div>
        </div>

      </div>
      <ModalPosts 
      isModal={isModal} 
      handlePostModal={handlePostModal} 
      name="Nguyễn Văn A" 
      img=""
      handlPostContent={handlPostContent}
      />
      
    </div>
  );
};

export default CreatePost;
