package io.ruin.data.impl.items;

import com.google.gson.annotations.Expose;
import io.ruin.api.utils.JsonUtils;
import io.ruin.api.utils.ServerWrapper;
import io.ruin.cache.ItemDef;
import io.ruin.data.DataFile;
import io.ruin.model.achievements.Achievement;
import io.ruin.model.combat.RangedAmmo;
import io.ruin.model.combat.RangedWeapon;
import io.ruin.model.stat.StatType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class equip_sounds extends DataFile {

    @Override
    public String path() {
        return "items/equip_sounds.json";
    }

    @Override
    public Object fromJson(String fileName, String json) {
        List<Temp> temps = JsonUtils.fromJson(json, List.class, Temp.class);
        temps.forEach(temp -> {
            ItemDef def = ItemDef.get(temp.itemid);
            if(def != null)
                def.soundId = temp.soundid;

        });
        return temps;
    }



    public static final class Temp {
        @ Expose public int itemid;
        @ Expose public int soundid;
    }

}