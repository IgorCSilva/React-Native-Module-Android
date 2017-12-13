
## JUnit

    Utilizando o JUnit para testes isoladamente, configurei o arquivo gradle e criei a pasta de testes 'test'. Nela criei três arquivos, um sendo a suíte de testes e os outros dois sendo os testes. Na suíte de testes implementei basicamente o código a seguir:
    
@RunWith(Suite.class)
@Suite.SuiteClasses({
        notificationTests.class,
        testIntents.class
})

public class TestRNMA {

}

    Desta forma os testes ficam mais organizados e simples de se entender. Na classe notificationTests temos teste referente ao padrão de vibração e na testIntents temos verificação de número telefônico de modo simples, apenas checando a quantidade de números que a string contém. A seguir temos os códigos de teste das duas classes:

- notificationTests.class:

@Test
public void testSimpleNotification(){

    long[] vibrate = {0, 100, 200, 300};

    SimpleNotification notification = new SimpleNotification(vibrate, 10, "Show", "De bolas");

    assertArrayEquals("Os padrões devem ser iguais", vibrate, notification.getVibrate());
    assertEquals(10, notification.getId());
}

- testIntents.class:

@Test
public void testLengthNumber(){

    IntentCall call = new IntentCall("81995474541");

    assertEquals(11, call.getLengthNumber());
}



## Espresso

    Primeiro foi definida a activity onde se queria realizar os testes utilizando ActivityTestRule.
    
@Rule
public ActivityTestRule<DownloadViewActivity>
    imageActivityTestRule = new ActivityTestRule<DownloadViewActivity>(DownloadViewActivity.class, true, false);
    

	  A activity escolhida foi a DownloadViewActivity.class, que contém uma imagem com id imagemDownload. O último parâmetro do construtor indica se a activity deve estar visível ou não. Colocando true habilitei para que a activity ficasse visível. Após isso implementei o teste a seguir:
    
@Test
public void viewImageLaunched(){

    onView(withId(R.id.imagemDownload)).check(matches(isDisplayed()));
}

Esse teste checa se a view que tem o id imagemDownload está sendo mostrada ou não. Como a activity ficou visível e a imagem também, então passou pelo teste. 
