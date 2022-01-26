package com.gmy.datastructures.innerclass;

import java.util.List;

/**
 * @Author guomaoyang
 * @Date 2021/2/2
 */
public class WXRobotTextMsgModel {
    private String msgtype = "text";
    private WXRobotTextMsgModel.Text text;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public WXRobotTextMsgModel.Text getText() {
        return text;
    }

    public void setText(WXRobotTextMsgModel.Text text) {
        this.text = text;
    }

    static class Text{
        private String content;
        private List<String> mentioned_mobile_list;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getMentioned_mobile_list() {
            return mentioned_mobile_list;
        }

        public void setMentioned_mobile_list(List<String> mentioned_mobile_list) {
            this.mentioned_mobile_list = mentioned_mobile_list;
        }

        @Override
        public String toString() {
            return "Text{" +
                    "content='" + content + '\'' +
                    ", mentioned_mobile_list=" + mentioned_mobile_list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WXRobotTextMsgModel{" +
                "msgtype='" + msgtype + '\'' +
                ", text=" + text +
                '}';
    }
}
