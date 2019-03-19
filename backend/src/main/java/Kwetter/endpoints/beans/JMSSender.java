package Kwetter.endpoints.beans;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JMSSender
{
  public void send(String messageString)
  {
    try
    {
      Properties props = new Properties();
      props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
      props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
      //props.put("queue", "queue.kwetter");

      Context jndiContext = new InitialContext(props);
      ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/__defaultConnectionFactory");
      Connection connection = connectionFactory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      Destination destination = (Destination) jndiContext.lookup("queue.kwetter");
      MessageProducer producer = session.createProducer(destination);
      TextMessage textMessage = session.createTextMessage();
      textMessage.setText(messageString);

      producer.send(textMessage);
    } catch (NamingException | JMSException e)
    {
      e.printStackTrace();
    }
  }
}
