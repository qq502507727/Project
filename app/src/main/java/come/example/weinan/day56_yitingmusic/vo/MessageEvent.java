package come.example.weinan.day56_yitingmusic.vo;


import come.example.weinan.day56_yitingmusic.utils.MessageEventType;

/**
 * description:
 * company: moliying.com
 * Created by vince on 16/8/12.
 */
public class MessageEvent {
    public MessageEventType type;
    public Object data;

    public MessageEvent(MessageEventType type) {
        this.type = type;
    }
}
