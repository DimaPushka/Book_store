package LearnUp.PushkaHW_34.messagesService;

import antlr.debug.MessageEvent;
import antlr.debug.MessageListener;
import antlr.debug.TraceEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.logging.log4j.message.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer implements MessageListener {

    @JmsListener(destination = "${topic.name}")
    @Override
    public void onMessage(Message message) {
        try{
            ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
            String msg = textMessage.getText();
            log.info("Received Message: "+ msg);
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }
    }

    @Override
    public void reportError(MessageEvent messageEvent) {

    }

    @Override
    public void reportWarning(MessageEvent messageEvent) {

    }

    @Override
    public void doneParsing(TraceEvent traceEvent) {

    }

    @Override
    public void refresh() {

    }
}
