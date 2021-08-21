package models.validators;

import actions.views.CommentView;
import constants.MessageConst;

public class CommentValidator {
    public static String validate(CommentView cv) {
        String content = cv.getContent();
        if(content == null || content.equals("")) {
            return MessageConst.E_NOCOMMENT.getMessage();
        }
        return null;
    }


}
