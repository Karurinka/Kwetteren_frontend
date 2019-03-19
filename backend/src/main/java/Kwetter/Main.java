package Kwetter;

import javax.annotation.Resource;
import javax.jms.*;

public class Main
{


  @Resource(mappedName = "jms/kwetter")
  private static ConnectionFactory connectionFactory;
  @Resource(mappedName = "jms/kwetterQueue")
  private static Queue queue;

  public void produceMessages()
  {
    MessageProducer messageProducer;
    TextMessage textMessage;
    try
    {
      Connection connection = connectionFactory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      messageProducer = session.createProducer(queue);
      textMessage = session.createTextMessage();

      textMessage.setText("1,Hello world");
      System.out.println("Sending the following message: " + textMessage.getText());
      messageProducer.send(textMessage);

      messageProducer.close();
      session.close();
      connection.close();
    } catch (JMSException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    try
    {

      // Start connection
      ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();

      Connection connection = cf.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      Destination destination = session.createQueue("kwetterQueue");
      MessageProducer producer = session.createProducer(destination);
      connection.start();

      // create message to send
      TextMessage message = session.createTextMessage();
      message.setText("1,Hello world from client app");

      System.out.println("Send from HelloProducer.java");
      producer.send(message);

      // close everything
      producer.close();
      session.close();
      connection.close();

    } catch (JMSException ex)
    {
      System.out.println("Error = " + ex.getMessage());
    }

  }
}
