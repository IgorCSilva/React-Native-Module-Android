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
  ScrollView,
  TextInput,
  Picker,
  AppState,
  Platform, 
  ToastAndroid
} from 'react-native';

import func from './functions';

import PushNotification from 'react-native-push-notification';
import PushController from './notifications';

import BackgroundTimer from 'react-native-background-timer';
import BackgroundTas from 'react-native-background-task';

// Módulo contendo métodos relacionados a toasts.
var ModuleAndroid = NativeModules.ToastModule;


export default class app extends Component {

  constructor(props) {
    super(props);

    //this.handleAppStateChange = this.handleAppStateChange.bind(this);


    this.state = {       
      seconds: 5,

      toastText: '',
      intentText: '',



      dataBaseId: '',
      dataBaseName: '',
      dataBaseSurname:'',
      dataBaseMarks: ''      
    };
  }

  componentDidMount(){
    //AppState.addEventListener('change', this.handleAppStateChange);
  }

  componentWillUnmount(){
   // AppState.removeEventListener('change', this.handleAppStateChange);
  
  }
  /*
  handleAppStateChange(appState){

    if(appState === 'background'){

      let date = new Date(Date.now() + (this.state.seconds * 1000));

      if(Platform.OS === 'ios'){
        date = date.toISOString();
      }

      //PushNotification.cancelLocalNotifications({id: '123'});

      PushNotification.localNotificationSchedule({
        id: '0',
        title: 'My First Notif',
        message: "My Notification Message", // (required)
        date,  // in 60 secs
      });
    } 
  }*/

  inputs(inputs){

    var list_inputs = inputs.map(function(data){
    
      return(
        <TextInput
          key={data.key} 
          style={{height: 40, borderColor: 'gray', borderWidth: 1}}
          placeholder={data.placeholder}
          onChangeText={data.onChange}
          value={data.value}
        />

      );
      
    }); 

    return(
      <View style={{}}>
            
        {list_inputs}            

      </View>
    );
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

  newBlock(title, inputs, buttons){

    var entradas;

    if(inputs.length == 0){
      entradas = <View/>
    }else {
      entradas = this.inputs(inputs);
    }

    return(
      <View style={{width: '100%', marginTop: 10}}>
          <Text style={{alignSelf: 'center', fontSize: 20, marginVertical: 5, borderBottomWidth: 1}}>
            {title}
          </Text>

          {entradas}

          {this.buttons(buttons)}

          <View style={{width: '100%', borderWidth: 1, borderColor: '#3355bb88', marginBottom: 10, marginTop: 10, elevation: 2}} />

          {/*
          
        */}
      
        </View>
    );
  }

  render() {

    

    var that = this;
    return (
      <View style={styles.container}>
        <ScrollView>

          
        {this.newBlock(
          "Toasts ",
          [
            {
              key: 1,
              placeholder: 'Texto',
              onChange: (text) => this.setState({toastText: text}),
              value: this.state.toastText
            }            
          ],

          [
            {
              text: "Toast Simples",
              func: () => func.toastSimples(this.state.toastText)   
            },
            {
              text: "Toast Personalizado",
              func: () => func.toastPers(this.state.toastText)   
            },
            
          ]
          )}

           {this.newBlock(
          "Toasts RN",
          [     
          ],

          [
            {
              text: "Toast Simples",
              func: () => func.toastRNSimples()   
            },
            
          ]
          )}

          {this.newBlock(
          "Intents",
          [
            {
              key: 2,
              placeholder: 'Digite um site ou rua',
              onChange: (text) => this.setState({intentText: text}),
              value: this.state.intentText
            }            
          ],

          
          [
            {
              text: "Site",
              func: () => func.intentSite("http://" + this.state.intentText)   
            },
            {
              text: "Localização",
              func: () => func.intentGeolocation("geo:0,0?q=" + this.state.intentText)   
            },
            {
              text: "Chamada",
              func: () => func.intentCall("tel:081995213474")   
            },
            {
              text: "Música",
              func: () => func.intentPlaySongSdCard("file:/storage/extSdCard/Áudios/Músicas/Maré.mp3")   
            },
          ]
          )}


          {this.newBlock(
          "BroadcastReceiver",
          [          
          ],

          
          [
            {
              text: "Enviar Broadcast",
              func: () => func.sendReactBroadcast("react.broadcasts.exemplo")   
            },
          ]
          )}

          {this.newBlock(
          "Notif. RN normal",
          [          
          ],
          
          [
            {
              text: "Notifica",
              func: () => func.notifica()   
            },
          ]
          )}

          <View>
            <Text> Escolha o tempo da notificação.</Text>
            <Picker
              style={{width: 100}}
              selectedValue={this.state.seconds}
              onValueChange={(seconds) => this.setState({ seconds })}
            >
              <Picker.Item label="5" value={5} />
              <Picker.Item label="10" value={10} />
              <Picker.Item label="15" value={15} />
            </Picker>
            <PushController />
          </View>

          {this.newBlock(
          "Notif. RN scheduled",
          [          
          ],
          
          [
            {
              text: "Notifica",
              func: () => func.notificaSch()   
            },
          ]
          )}
          
          {this.newBlock(
          "Notificações",
          [        
          ],

          
          [
            {
              text: "Notificação Simples",
              func: () => func.simpleNotification(23, "Module Android", "React Native")   
            },
            {
              text: "Notificação Personalizada",
              func: () => func.customNotification(13, "Module Android", "React Native açsldkfj açsldkfj açsldkfj açsldkfj")   
            },
            {
              text: "Notificação Com Ações",
              func: () => func.actionNotification(7, "Module Android", "React Native", "Ação 1", "Ação 2")   
            },
            {
              text: "Notificação Inbox",
              func: () => func.inboxNotification(17, "Inbox Notification", ['', ''], "Sumário da notificação")   
            },
            {
              text: "Notificação - Texto Grande",
              func: () => func.bigTextNotification(11, "Big Text Notification", "Escrever um texto grande para que fique com duas linhas ou mais na notificação.", "Sumário da notificação")   
            },
            {
              text: "Notificação - Imagem Grande",
              func: () => func.bigPictureNotification(9, "Big Image Notification", "Sumário da notificação")   
            },
            {
              text: "Notificação Settings",
              func: () => func.settingsNotification(123, "Module Android")   
            },
          ]
          )}


          {/*
          {this.newBlock(
          "Callbacks",
          [
                   
          ],

          
          [
            {
              text: "Callback",
              func: () => func.callback()   
            },
          ]
          )}*/}
          
          {/**/}
          {this.newBlock(
          "AlertDialog",
          [
                    
          ],

          
          [
            {
              text: "AlertDialog",
              func: () => func.alertDialog()   
            },
          ]
          )}


          {/**/}
          {this.newBlock(
          "Player",
          [
                      
          ],

          
          [
            {
              text: "Play",
              func: () => func.player("play")   
            },
            {
              text: "Pause",
              func: () => func.player("pause")   
            },
            {
              text: "Stop",
              func: () => func.player("stop")   
            },
          ]
          )}

          {/**/}
          {this.newBlock(
          "Service - Player No Bind",
          [
                    
          ],

          
          [
            {
              text: "startService",
              func: () => func.serviceNoBind("start")   
            },
            {
              text: "stopService",
              func: () => func.serviceNoBind("stop")   
            },
          ]
          )}

          {/**/}
          {this.newBlock(
          "Service - Player With Bind",
          [
                    
          ],

          
          [
            {
              text: "Binding",
              func: () => func.serviceWithBind("bind")   
            },
            {
              text: "Unbinding",
              func: () => func.serviceWithBind("unbinding")   
            },
            {
              text: "Play",
              func: () => func.serviceWithBind("start")   
            },
            {
              text: "Pause",
              func: () => func.serviceWithBind("pause")   
            },
          ]
          )}

          

          {/**/}
          {this.newBlock(
          "Service - Download",
          [
                    
          ],

          
          [
            {
              text: "Download",
              func: () => func.serviceDownload("download", "https://i.pinimg.com/originals/34/fb/df/34fbdf18c97b00e0ad88df836b3566d9.jpg")   
            },
            {
              text: "Visializar",
              func: () => func.serviceDownload("visualizar", "https://i.pinimg.com/originals/34/fb/df/34fbdf18c97b00e0ad88df836b3566d9.jpg")   
            },
          ]
          )}
          

          {/**/}
          {this.newBlock(
          "Memória Externa",
          [
                   
          ],

          
          [
            {
              text: "Copiar",
              func: () => func.memoriaExterna("copiar")   
            },
            {
              text: "Visializar",
              func: () => func.memoriaExterna("ler")                 
            },
            {
              text: "Apagar",
              func: () => func.memoriaExterna("apagar")                 
            },
          ]
          )}

          {this.newBlock(
          "Banco de Dados",
          [
            
            {
              key: 110,
              placeholder: 'Id',
              onChange: (text) => this.setState({dataBaseId: text}),
              value: this.state.dataBaseId
            },
            {
              key: 111,
              placeholder: 'Nome',
              onChange: (text) => this.setState({dataBaseName: text}),
              value: this.state.dataBaseName
            },
            {
              key: 112,
              placeholder: 'Sobrenome',
              onChange: (text) => this.setState({dataBaseSurname: text}),
              value: this.state.dataBaseSurname
            },
            {
              key: 113,
              placeholder: 'Marks',
              onChange: (text) => this.setState({dataBaseMarks: text}),
              value: this.state.dataBaseMarks
            },
                 
          ],
          
          [
            {
              text: "Inserir",
              func: () => func.sqlDatabase("insert", this.state.dataBaseId, this.state.dataBaseName, this.state.dataBaseSurname, this.state.dataBaseMarks)   
            },
            {
              text: "Ler",
              func: () => func.sqlDatabase("read", this.state.dataBaseId, this.state.dataBaseName, this.state.dataBaseSurname, this.state.dataBaseMarks)                 
            },
            {
              text: "Atualizar",
              func: () => func.sqlDatabase("update", this.state.dataBaseId, this.state.dataBaseName, this.state.dataBaseSurname, this.state.dataBaseMarks)   
            },
            {
              text: "Apagar",
              func: () => func.sqlDatabase("delete", this.state.dataBaseId, this.state.dataBaseName, this.state.dataBaseSurname, this.state.dataBaseMarks)                 
            }
          ]
          )}


           {/*this.newBlock(
          "Processos",
          [],
          
          [
            {
              text: "Thread",
              func: () => func.processes("thread")   
            },
            
          ]
          )*/}

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

AppRegistry.registerComponent('app', () => app);
