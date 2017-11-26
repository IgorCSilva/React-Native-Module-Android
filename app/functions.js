/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Button,
  TouchableOpacity,
  NativeModules,
  ScrollView
} from 'react-native';

// Módulo contendo métodos relacionados a toasts.
var ModuleAndroid = NativeModules.ToastModule;


  /**
   * Exibe um toast simples com a mensagem enviada.
   * @param {*mensagem} msg
   */
  module.exports.toastSimples = (msg) => {

    ModuleAndroid.showToastSimples(msg);
  }

  /**
   * Exibe um toast personalizado com a mensagem enviada.
   * @param {*} msg 
   */
  module.exports.toastPers = (msg) => {
    
    ModuleAndroid.showToastPersonalizado(msg);
  }

  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.intentSite = (site) => {

    ModuleAndroid.intentSite(site);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.intentGeolocation = (local) => {

    ModuleAndroid.intentGeolocation(local);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.intentCall = (number) => {

    ModuleAndroid.intentCall(number);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.intentPlaySongSdCard = (song) => {

    ModuleAndroid.intentPlaySongSdCard(song);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.sendReactBroadcast = (broadcast) => {

    ModuleAndroid.sendReactBroadcast(broadcast);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.simpleNotification = (id, title, text) => {

    ModuleAndroid.simpleNotification(id, title, text);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.customNotification = (id, title, text) => {

    ModuleAndroid.customNotification(id, title, text);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.actionNotification = (id, title, text, action1, action2) => {

    ModuleAndroid.actionNotification(id, title, text, action1, action2);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.inboxNotification = (id, title, texts, summary) => {

    ModuleAndroid.inboxNotification(id, title, summary);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.bigTextNotification = (id, title, bigText, summary) => {

    ModuleAndroid.bigTextNotification(id, title, bigText, summary);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.bigPictureNotification = (id, title, summary) => {

    ModuleAndroid.bigPictureNotification(id, title, summary);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.settingsNotification = (id, title) => {

    ModuleAndroid.settingsNotification(id, title);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.callback = () => {

    ModuleAndroid.callback((msg) => this.customNotification(33, "Callback", msg));
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.alertDialog = () => {

    ModuleAndroid.alertDialog();
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.player = (command) => {

    ModuleAndroid.player(command);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.serviceNoBind = (command) => {
    //alert(command);

    ModuleAndroid.serviceNoBind(command);
  }

  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.serviceWithBind = (command) => {
    //alert(command);

    ModuleAndroid.serviceWithBind(command);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.serviceDownload = (command, link) => {
    //alert(command);

    ModuleAndroid.serviceDownload(command, link);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.memoriaExterna = (command) => {
    //alert(command);

    ModuleAndroid.memoriaExterna(command);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.sqlDatabase = (command, id, name, surname, marks) => {

    ModuleAndroid.sqlDatabase(command, id, name, surname, marks);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  module.exports.processes = (command) => {

    ModuleAndroid.processes(command);
  }


