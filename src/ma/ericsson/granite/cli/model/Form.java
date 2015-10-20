package ma.ericsson.granite.cli.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbenhammane on 10/19/15.
 */
public class Form {

    private String name;
    private String description;
    private String manager;
    private String model;
    private String pk;
    private String sic;

    private List<Combo> comboList = new ArrayList<>();
    private List<Field> fieldList = new ArrayList<>();
    private List<Button> buttonList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getSic() {
        return sic;
    }

    public void setSic(String sic) {
        this.sic = sic;
    }

    public List<Combo> getComboList() {
        return comboList;
    }

    public void setComboList(List<Combo> comboList) {
        this.comboList = comboList;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }

	public boolean hasAttachment() {
		for(Field field : getFieldList()){
			if(field.isFile()){
				return true;
			}
		}
		return false;
	}
}
