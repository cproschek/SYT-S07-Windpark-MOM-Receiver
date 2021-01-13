package windpark;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.Iterator;

public class MOMReceiver {

  private static String user = ActiveMQConnection.DEFAULT_USER;
  private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
  private static String subject = "windengine.>";
  private static ArrayList<String> JSONs;

  public MOMReceiver() {

	  System.out.println( "Receiver started." );
	  JSONs = new ArrayList<String>();

	  // Create the connection.
	  Session session = null;
	  Connection connection = null;
	  MessageConsumer consumer = null;
	  Destination destination = null;

	  try {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
		connection = connectionFactory.createConnection();
		connection.start();

		// Create the session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue( subject );

		// Create the consumer
		consumer = session.createConsumer( destination );

		// Start receiving
		TextMessage message = (TextMessage) consumer.receive();
		while ( message != null ) {
			System.out.println("Message received: " + message.getText() );
			JSONs.add(message.getText());
			message.acknowledge();
			message = (TextMessage) consumer.receive();
		}
		connection.stop();

	  } catch (Exception e) {

	  	System.out.println("[MessageConsumer] Caught: " + e);
	  	e.printStackTrace();

	  } finally {

			try { consumer.close(); } catch ( Exception e ) {}
			try { session.close(); } catch ( Exception e ) {}
			try { connection.close(); } catch ( Exception e ) {}

	  }
	  System.out.println( "Receiver finished." );

  } // end main

	public static String getJSON(){
  		StringBuilder temp = new StringBuilder();
		temp.append("[");
		Iterator<String> it = JSONs.iterator();
		while(it.hasNext()) {
			temp.append(it.next());
			if(it.hasNext())
				temp.append(", ");
		}
		temp.append("]");
  		return temp.toString();
	}
}
