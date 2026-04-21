package net.lobster.rockets_aint_cheap.world;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AsteroidSavedData extends SavedData {

    private static final String DATA_NAME = "rockets_aint_cheap_asteroids";

    private final Map<String, BlockPos> stationCenters = new HashMap<>();

    public void setStationCenter(String dim, BlockPos pos) {
        stationCenters.put(dim, pos);
        setDirty();
    }

    public BlockPos getStationCenter(String dim) {
        return stationCenters.getOrDefault(dim, BlockPos.ZERO);
    }

    private final Set<String> generatedChunks = new HashSet<>();

    public static AsteroidSavedData load(CompoundTag tag) {
        AsteroidSavedData data = new AsteroidSavedData();

        ListTag list = tag.getList("chunks", 8); // 8 = StringTag
        for (int i = 0; i < list.size(); i++) {
            data.generatedChunks.add(list.getString(i));
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();

        for (String key : generatedChunks) {
            list.add(StringTag.valueOf(key));
        }

        tag.put("chunks", list);

        return tag;
    }

    public static AsteroidSavedData get(net.minecraft.server.level.ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                AsteroidSavedData::load,
                AsteroidSavedData::new,
                DATA_NAME
        );
    }

    public boolean isGenerated(String key) {
        return generatedChunks.contains(key);
    }

    public void markGenerated(String key) {
        generatedChunks.add(key);
        setDirty();
    }
}