package javax.media.rtp.event;

import javax.media.rtp.*;

/**
 * Standard JMF class -- see <a href=
 * "http://java.sun.com/products/java-media/jmf/2.1.1/apidocs/javax/media/rtp/event/StreamMappedEvent.html"
 * target="_blank">this class in the JMF Javadoc</a>. Complete.
 * 
 * @author Ken Larson
 * 
 */
public class StreamMappedEvent extends ReceiveStreamEvent
{
    public StreamMappedEvent(SessionManager from, ReceiveStream stream,
            Participant participant)
    {
        super(from, stream, participant);
    }

}
