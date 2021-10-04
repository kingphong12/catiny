import React, {useState} from 'react';
import {useAppDispatch, useAppSelector} from 'app/config/store';
import { uploadFile } from './reducer/master-user.reducer';
import axios from 'axios';
// var FormData = require('form-data');
// var request = require('request');


const MultipleUpload = (props) => {
  const {id, label} = props
  const dispatch = useAppDispatch();

  const [imageUpload, setImageUpload] = useState("")
  const [videoUpload, setVideoUpload] = useState("")


  const handleUploadFile = async (e) => {
    console.log(e.target.files[0]);
    const formData = new FormData();

    formData.append('imageData', e.target.files[0])
    // formData.append('desiredName', "meo-meo")
   await axios.post( '/api/o/images/upload',
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
  })
  .catch(function(){
    console.log('FAILURE!!');
  });
  }  

  return (
    <div className="upload" style={{position: "relative"}}>
      <div className="form-group" style={{position: "relative", display: "flex"}} onChange={handleUploadFile}>
        <input type="file" name={id} className="form-contol uploader-input" style={{width: "10rem", height: "10rem", opacity: "0", position: "absolute"}} />
        <label htmlFor={id}>{label}</label>
      </div>
      {/* <div className="gruop-input"> */}
        {
         imageUpload && <img src={imageUpload} alt="upload" style={{width: "16rem", objectFit: "cover"}} />
        }
      {/* </div> */}

      <div className="form-group" style={{position: "relative", display: "flex"}} onChange={handleUploadFileVideo}>
        <input type="file" name="video" className="form-contol uploader-input" style={{width: "10rem", height: "10rem", opacity: "0", position: "absolute"}} />
        <label htmlFor="video">Video</label>
      </div>
      {
       videoUpload && <video style={{width: "16rem", objectFit: "cover"}} controls src={videoUpload}></video>
      }
    </div>
  );
};

export default MultipleUpload;
