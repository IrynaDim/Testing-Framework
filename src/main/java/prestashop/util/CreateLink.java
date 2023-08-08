package prestashop.util;

import com.aventstack.extentreports.markuputils.Markup;

public class CreateLink implements Markup {

    private final String filepath;
    private final String linkName;

    public CreateLink(String filepath, String linkName) {
        this.filepath = filepath;
        this.linkName = linkName;
    }


    public String getMarkup() {
        return "<a href='" + filepath + "' target='_new'style = 'color:blue;'><u>" + linkName + "</u></a>";
    }

}
