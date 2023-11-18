package DAO;

import java.util.List;
import model.TimVe;

/**
 *
 * @author Tri Dung
 */
public abstract class CinemaxDAO<EntityType, KeyType> {
    public abstract void insert(EntityType entity);
    public abstract void update(EntityType entity);
    public abstract void delete(KeyType id);
    public abstract List<EntityType> selectAll();
    public abstract EntityType selectById(KeyType id);
    public abstract List<EntityType> selectBySql(String sql, Object... args);
    public abstract List<TimVe> FintTicket(String ThoiGian, String PhongChieu, String Phim);
}
