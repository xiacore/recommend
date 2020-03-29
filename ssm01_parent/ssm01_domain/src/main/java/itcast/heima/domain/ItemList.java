package itcast.heima.domain;

import java.io.Serializable;
import java.util.List;

public class ItemList implements Serializable {
    private List<Item_Chara> item_charaList;

    public List<Item_Chara> getItem_charaList() {
        return item_charaList;
    }

    public void setItem_charaList(List<Item_Chara> item_charaList) {
        this.item_charaList = item_charaList;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "item_charaList=" + item_charaList +
                '}';
    }
}
