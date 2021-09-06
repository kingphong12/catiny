import SockJS from 'sockjs-client';

import Stomp from 'webstomp-client';
import {Observable} from 'rxjs';
import {Storage} from 'react-jhipster';
import {useAppSelector} from './store';

export default class Websocket
{
  public static readonly TOPIC_CONSUMER = "/topic.consumer";
  public static readonly TOPIC_PRODUCER = "/topic.producer";

  private stompClient = null;
  private subscriber = null;
  private connection: Promise<any>;
  private connectedPromise: any = null;
  private listener: Observable<any>;
  private listenerObserver: any;
  private alreadyConnectedOnce = false;
  private masterUser = useAppSelector(state => state.authentication.masterUser)
  public readonly TOPIC_USER_PRODUCER = `/user/${this.masterUser.uuid}/topic.producer`;
  public readonly TOPIC_USER_CONSUMER = `/user/${this.masterUser.uuid}/topic.consumer`;

  private _logDebug = false;

  set logDebug(value: boolean)
  {
    this._logDebug = value;
  }

  send(topic: string, data: any)
  {
    this.connection?.then(() =>
      this.stompClient?.send(
        topic, // destination
        JSON.stringify(data), // body
        {} // header
      ));
  }

  sendProducer(topic: string, data: any)
  {
    topic = topic.indexOf("/") === 0 ? topic.substring(1) : topic;
    this.connection?.then(() =>
      this.stompClient?.send(
        `${Websocket.TOPIC_PRODUCER}/${topic}`, // destination
        JSON.stringify(data), // body
        {} // header
      ));
  }

  sendUserProducer(topic: string, data: any)
  {
    topic = topic.indexOf("/") === 0 ? topic.substring(1) : topic;
    this.connection?.then(() =>
      this.stompClient?.send(
        `${this.TOPIC_USER_PRODUCER}/${topic}`, // destination
        JSON.stringify(data), // body
        {} // header
      ));
  }

  /**
   *
   * @param topic   topic name (/topic/{topic})
   * @param process function process
   */
  subscribe(topic: string, process)
  {
    topic = topic.indexOf("/topic/") === 0 ? topic.substring(7) : topic
    topic = topic.indexOf("topic/") === 0 ? topic.substring(6) : topic
    topic = topic.indexOf("/") === 0 ? topic.substring(1) : topic
    this.connection.then(() =>
      this.subscriber = this.stompClient.subscribe(`/topic/${topic}`, data =>
      {
        process(data);
        // this.listenerObserver.next( JSON.parse(data.body));
      })
    );
  }

  /**
   *
   * @param topic   topic name (/topic.consumer/{topic})
   * @param process function process
   */
  subscribeConsumer(topic: string, process)
  {
    topic = topic.indexOf("/") === 0 ? topic.substring(1) : topic
    this.connection.then(() =>
      this.subscriber = this.stompClient.subscribe(`/topic.consumer/${topic}`, data =>
      {
        process(data);
        // this.listenerObserver.next( JSON.parse(data.body));
      })
    );
  }

  /**
   *
   * @param topic   topic name (/user/{uuid}/topic.consumer/{topic})
   * @param process function process
   */
  subscribeUserConsumer(topic: string, process)
  {
    topic = topic.startsWith("/") ? topic.substring(1) : topic
    this.connection.then(() =>
      this.subscriber = this.stompClient.subscribe(`/user/${this.masterUser.uuid}/topic.consumer/${topic}`, data =>
      {
        process(data);
        // this.listenerObserver.next( JSON.parse(data.body));
      })
    );
  }

  connect(path: string)
  {
    this.connectAndSend(path, null);
  }

  connectAndSend(path: string, sendBeforeConnect: { topic: string, data })
  {
    if (this.connectedPromise !== null || this.alreadyConnectedOnce)
      return; // the connection is already being established

    this.connection = this.createConnection();
    this.listener = this.createListener();

    // building absolute path so that websocket doesn't fail when deploying with a context path
    const loc = window.location;
    const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');

    const headers = {};
    let url = '//' + loc.host + baseHref + path;
    const authToken = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
    if (authToken)
      url += '?access_token=' + authToken;
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket, {protocols: ['v12.stomp']});
    if (!this._logDebug)
      this.stompClient.debug = () =>
      {
      };// don't show debug console log

    this.stompClient.connect(headers, () =>
    {
      this.connectedPromise('success');
      this.connectedPromise = null;
      if (sendBeforeConnect && sendBeforeConnect.topic && sendBeforeConnect.data)
        this.send(sendBeforeConnect.topic, sendBeforeConnect.data);
      this.alreadyConnectedOnce = true;
    });
  };

  createConnection()
  {
    return new Promise(resolve => this.connectedPromise = resolve);
  }

  createListener()
  {
    return new Observable(observer => this.listenerObserver = observer);
  }

  disconnect()
  {
    if (this.stompClient !== null)
    {
      if (this.stompClient.connected)
        this.stompClient.disconnect();
      this.stompClient = null;
    }
    this.alreadyConnectedOnce = false;
  };

  receive = () => this.listener;

  unsubscribe()
  {
    if (this.subscriber !== null)
      this.subscriber.unsubscribe();
    this.listener = this.createListener();
  };

  // cái gì đó để tìm hiểu sau
  // a()
  // {
  //   return store => next => action =>
  //   {
  //     if (getAccount.fulfilled.match(action))
  //     {
  //       this.connect();
  //       const isAdmin = action.payload.data.authorities.includes('ROLE_ADMIN');
  //       if (!this.alreadyConnectedOnce && isAdmin)
  //       {
  //         this.subscribe();
  //         this.receive().subscribe(activity =>
  //         {
  //           return store.dispatch(websocketActivityMessage(activity));
  //         });
  //       }
  //     }
  //     else if (getAccount.rejected.match(action) || action.type === logoutSession().type)
  //     {
  //       this.unsubscribe();
  //       this.disconnect();
  //     }
  //     return next(action);
  //   };
  // }
}
