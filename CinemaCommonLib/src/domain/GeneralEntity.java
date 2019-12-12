/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Monika
 */
public interface GeneralEntity extends Serializable {

    public String getTableName();

    public List<GeneralEntity> getList(ResultSet resultSet) throws Exception;

    public String getColumnName();

    public String getValueForInsert();

    public String getValueForUpdate();

    public String getWhere();

    public String getColumnForSelection();

    public String getAlias();

    public String getJoin();

    public String getWhereForSelect();

    public String getGroup();

    public String getMaxPrimary();

}
