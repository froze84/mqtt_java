package listener;
import java.*;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
public class Listener implements MqttCallback {
    private static final String brokerUrl = "tcp://localhost:1883";
    private static final String clientId = "Client_listener";
    //static Scanner in=new Scanner(System.in);
    //System.out.println("checking");
    private static String topic ;
    private static String message;
    public static void main(String[] args) {
        System.out.println("input topic");
        Scanner in=new Scanner(System.in);
        topic=in.nextLine();
        new Listener().subscribe(topic);
    }
    public void subscribe(String topic) {

        MemoryPersistence persistence = new MemoryPersistence();

        try {

            MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("checking");

            System.out.println("Mqtt Connecting to broker: " + brokerUrl);
            sampleClient.connect(connOpts);
            System.out.println("Mqtt Connected");

            sampleClient.setCallback(this);
            sampleClient.subscribe(topic);

            System.out.println("Subscribed");
            System.out.println("Listening");
            

        } catch (MqttException me) {

            System.out.println("Mqtt reason " + me.getReasonCode());
            System.out.println("Mqtt msg " + me.getMessage());
            System.out.println("Mqtt loc " + me.getLocalizedMessage());
            System.out.println("Mqtt cause " + me.getCause());
            System.out.println("Mqtt excep " + me);
        }
    }
    public void connectionLost(Throwable arg0) {
    }
    public void deliveryComplete(IMqttDeliveryToken arg0) {
    }
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Listener.topic=topic;
        Listener.message=message.toString();
        System.out.println("Mqtt topic : " + topic);
        System.out.println("Mqtt msg : " + message.toString());
        Excel_export.main();
    }
    public static String getTopic()
    {
        return topic;
    }
    public static String getMessage()
    {
        return message;
    }
    public static String getClientid()
    {
        return clientId;
    }
    
}