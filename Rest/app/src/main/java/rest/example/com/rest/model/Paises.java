package rest.example.com.rest.model;

/**
 * Created by thiag_000 on 29/03/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

        import java.util.HashMap;
        import java.util.Map;

public class Paises {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("latlng")
    @Expose
    private List<Double> latlng = new ArrayList<Double>();

    public String getName() {
        return name;
    }

    /**
     *
;     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }


    public List<Double> getLatlng(int i) {
        return latlng;
    }

    /**
     *
     * @param latlng
     *     The latlng
     */
    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }



}