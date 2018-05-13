package project.view.impl.container;

public enum Actions {
    CREATE("Create"), EDIT("Edit"), DELETE("Delete"), FIND("Find"), SAVE("Save"), CANCEL("Cancel");

    private String act;
    Actions(String action){
        this.act = action;
    }

    public String getAct() {
        return act;
    }
}
