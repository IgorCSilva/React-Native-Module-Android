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

export default class and extends Component {

  /**
   * Exibe um toast simples com a mensagem enviada.
   * @param {*mensagem} msg
   */
  toastSimples(msg){

    ModuleAndroid.showToastSimples(msg);
  }

  /**
   * Exibe um toast personalizado com a mensagem enviada.
   * @param {*} msg 
   */
  toastPers(msg){
    
    ModuleAndroid.showToastPersonalizado(msg);
  }

  /**
   * Exibe o browser na página do site escolhido.
   */
  intentSite(site){

    ModuleAndroid.intentSite(site);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  intentGeolocation(local){

    ModuleAndroid.intentGeolocation(local);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  intentCall(number){

    ModuleAndroid.intentCall(number);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  intentPlaySongSdCard(song){

    ModuleAndroid.intentPlaySongSdCard(song);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  sendReactBroadcast(broadcast){

    ModuleAndroid.sendReactBroadcast(broadcast);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  simpleNotification(id, title, text){

    ModuleAndroid.simpleNotification(id, title, text);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  customNotification(id, title, text){

    ModuleAndroid.customNotification(id, title, text);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  actionNotification(id, title, text, action1, action2){

    ModuleAndroid.actionNotification(id, title, text, action1, action2);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  inboxNotification(id, title, texts, summary){

    ModuleAndroid.inboxNotification(id, title, summary);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  bigTextNotification(id, title, bigText, summary){

    ModuleAndroid.bigTextNotification(id, title, bigText, summary);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  bigPictureNotification(id, title, summary){

    ModuleAndroid.bigPictureNotification(id, title, summary);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  settingsNotification(id, title){

    ModuleAndroid.settingsNotification(id, title);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  callback(){

    ModuleAndroid.callback((msg) => this.customNotification(33, "Callback", msg));
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  alertDialog(){

    ModuleAndroid.alertDialog();
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  player(command){

    ModuleAndroid.player(command);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  serviceNoBind(command){
    //alert(command);

    ModuleAndroid.serviceNoBind(command);
  }

  
  /**
   * Exibe o browser na página do site escolhido.
   */
  serviceWithBind(command){
    //alert(command);

    ModuleAndroid.serviceWithBind(command);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  serviceDownload(command, link){
    //alert(command);

    ModuleAndroid.serviceDownload(command, link);
  }
  
  /**
   * Exibe o browser na página do site escolhido.
   */
  memoriaExterna(command){
    //alert(command);

    ModuleAndroid.memoriaExterna(command);
  }



  buttons(buttons){

    var list_buttons = buttons.map(function(data){
      
      return(
        <TouchableOpacity
          key={data.text}
          style={{margin: 5, padding: 5, alignItems: 'center', backgroundColor: "#841584", borderRadius: 10, elevation: 5, borderColor: "#00000066", borderWidth: 2}}
          onPress={data.func}
          
        >
          <Text style={{color: 'white', fontSize: 20}}> {data.text} </Text>

        </TouchableOpacity>

      );
      
    }); 

    return(
      <View style={{}}>
            
        {list_buttons}            

      </View>
    );
  }

  newBlock(title, buttons){
    return(
      <View style={{width: '100%', marginTop: 10}}>
          <Text style={{alignSelf: 'center', fontSize: 20, marginVertical: 5, borderBottomWidth: 1}}>
            {title}
          </Text>

          {this.buttons(buttons)}

          <View style={{width: '100%', borderWidth: 1, borderColor: '#3355bb88', marginBottom: 10, marginTop: 10, elevation: 2}} />

          {/*
          
        */}
      
        </View>
    );
  }

  render() {

    var texts = new Array();
    texts[0] = "Text 1";
    texts[1] = "Text 2";
    texts[2] = "Text 3";
    texts[3] = "Text 4";
    texts[4] = "Text 5";

    return (
      <View style={styles.container}>
        <ScrollView>
        {this.newBlock(
          "Toasts ",
          [
            {
              text: "Toast Simples",
              func: () => this.toastSimples("Toast Simples")   
            },
            {
              text: "Toast Personalizado",
              func: () => this.toastPers("Toast Personalizado")   
            },
            
          ]
          )}

          {this.newBlock(
          "Intents",
          [
            {
              text: "Site",
              func: () => this.intentSite("http://www.cin.ufpe.br")   
            },
            {
              text: "Localização",
              func: () => this.intentGeolocation("geo:0,0?q=Rua Mamede Simoes")   
            },
            {
              text: "Chamada",
              func: () => this.intentCall("tel:995213474")   
            },
            {
              text: "Música",
              func: () => this.intentPlaySongSdCard("file:/storage/extSdCard/Áudios/Músicas/Maré.mp3")   
            },
          ]
          )}


          {this.newBlock(
          "BroadcastReceiver",
          [
            {
              text: "Enviar Broadcast",
              func: () => this.sendReactBroadcast("react.broadcasts.exemplo")   
            },
          ]
          )}
          
          {this.newBlock(
          "Notificações",
          [
            {
              text: "Notificação Simples",
              func: () => this.simpleNotification(23, "Module Android", "React Native")   
            },
            {
              text: "Notificação Personalizada",
              func: () => this.customNotification(13, "Module Android", "React Native açsldkfj açsldkfj açsldkfj açsldkfj")   
            },
            {
              text: "Notificação Com Ações",
              func: () => this.actionNotification(7, "Module Android", "React Native", "Ação 1", "Ação 2")   
            },
            {
              text: "Notificação Inbox",
              func: () => this.inboxNotification(17, "Inbox Notification", texts, "Sumário da notificação")   
            },
            {
              text: "Notificação - Texto Grande",
              func: () => this.bigTextNotification(11, "Big Text Notification", "Escrever um texto grande para que fique com duas linhas ou mais na notificação.", "Sumário da notificação")   
            },
            {
              text: "Notificação - Imagem Grande",
              func: () => this.bigPictureNotification(9, "Big Image Notification", "Sumário da notificação")   
            },
            {
              text: "Notificação Settings",
              func: () => this.settingsNotification(123, "Module Android")   
            },
          ]
          )}


          {/**/}
          {this.newBlock(
          "Callbacks",
          [
            {
              text: "Callback",
              func: () => this.callback()   
            },
          ]
          )}
          
          {/**/}
          {this.newBlock(
          "AlertDialog",
          [
            {
              text: "AlertDialog",
              func: () => this.alertDialog()   
            },
          ]
          )}


          {/**/}
          {this.newBlock(
          "Player",
          [
            {
              text: "Play",
              func: () => this.player("play")   
            },
            {
              text: "Pause",
              func: () => this.player("pause")   
            },
            {
              text: "Stop",
              func: () => this.player("stop")   
            },
          ]
          )}

          {/**/}
          {this.newBlock(
          "Service - Player No Bind",
          [
            {
              text: "startService",
              func: () => this.serviceNoBind("start")   
            },
            {
              text: "stopService",
              func: () => this.serviceNoBind("stop")   
            },
          ]
          )}

          {/**/}
          {this.newBlock(
          "Service - Player With Bind",
          [
            {
              text: "Binding",
              func: () => this.serviceWithBind("bind")   
            },
            {
              text: "Unbinding",
              func: () => this.serviceWithBind("unbinding")   
            },
            {
              text: "Play",
              func: () => this.serviceWithBind("start")   
            },
            {
              text: "Pause",
              func: () => this.serviceWithBind("pause")   
            },
          ]
          )}

          

          {/**/}
          {this.newBlock(
          "Service - Download",
          [
            {
              text: "Download",
              func: () => this.serviceDownload("download", "https://i.pinimg.com/originals/34/fb/df/34fbdf18c97b00e0ad88df836b3566d9.jpg")   
            },
            {
              text: "Visializar",
              func: () => this.serviceDownload("visualizar", "https://i.pinimg.com/originals/34/fb/df/34fbdf18c97b00e0ad88df836b3566d9.jpg")   
            },
          ]
          )}
          

          {/**/}
          {this.newBlock(
          "Memória Externa",
          [
            {
              text: "Copiar",
              func: () => this.memoriaExterna("copiar")   
            },
            {
              text: "Visializar",
              func: () => this.memoriaExterna("ler")                 
            },
            {
              text: "Apagar",
              func: () => this.memoriaExterna("apagar")                 
            },
          ]
          )}


        </ScrollView>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,    
    backgroundColor: '#ffffff',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('and', () => and);
