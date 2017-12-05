import React, { Component } from 'react';
import PushNotification from 'react-native-push-notification';

export default class PushController extends Component {

    componentDidMount(){
        PushNotification.configure({
            onNotification: function(notification){
                alert('lançou');
                console.log('Notification: ', notification);
            },

        });
    }

    render(){
        return null;
    }
}