package itcast.heima.domain;

import java.io.Serializable;

public class Item_Chara implements Serializable {
    private int instrument;
    private int rate;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getInstrument() {
        return instrument;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    @Override
    public String toString() {
        return "Item_Chara{" +
                "rate=" + rate +
                ", instrument='" + instrument + '\'' +
                '}';
    }
}
