import React, {Fragment} from "react";
import PageTitle from '../components/PageTitle';

const storyList = [
    {
        imageUrl: 'user.png',
        name: 'Aliqa Macale',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Hendrix Stamp',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Stephen Grider',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Mohannad Zitoun',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Aliqa Macale',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Surfiya Zakir',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Aliqa Macale',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Surfiya Zakir',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Aliqa Macale',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Surfiya Zakir',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Aliqa Macale',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },
    {
        imageUrl: 'user.png',
        name: 'Surfiya Zakir',
        email: 'support@gmail.com',
        bgImage: 'product.png',
    },

]

const Storie = () => (
  <Fragment>
    <div className="main-content right-chat-active">
      <div className="middle-sidebar-bottom">
        <div className="middle-sidebar-left pe-0">
          <div className="row">
            <div className="col-xl-12">

              <PageTitle title="Stories"/>

              <div className="row ps-2 pe-1">
                {storyList.map((value, index) => (

                  <div key={index} className="col-md-3 col-xss-6 pe-2 ps-2">
                    <div className="card h300 d-block border-0 shadow-xss rounded-3 bg-gradiant-bottom overflow-hidden mb-3 bg-image-cover"
                         style={{backgroundImage: `url("assets/images/${value.bgImage}")`}}>
                      <div className="card-body d-block w-100 position-absolute bottom-0 text-center">
                        <figure className="avatar ms-auto me-auto mb-0 position-relative w50 z-index-1"><img src={`assets/images/${value.imageUrl}`}
                                                                                                             alt="avater"
                                                                                                             className="float-right p-0 bg-white rounded-circle w-100 shadow-xss"/>
                        </figure>
                        <div className="clearfix"></div>
                        <h4 className="fw-600 position-relative z-index-1 ls-3 font-xssss text-white mt-2 mb-1">{value.name}</h4>
                      </div>
                    </div>
                  </div>

                ))}


              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </Fragment>
);

export default Storie;