package top.gingercat.content.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import top.gingercat.base.model.GeoPoint;
import top.gingercat.base.utils.GeoPointConverter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 经维度坐标类型处理器
 *
 * @author chendy
 */
@MappedTypes({GeoPoint.class})
public class GeoPointTypeHandler extends BaseTypeHandler<GeoPoint> {

    GeoPointConverter converter = new GeoPointConverter();

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, GeoPoint geoPoint, JdbcType jdbcType) throws SQLException {
        preparedStatement.setBytes(i, converter.to(geoPoint));
    }

    @Override
    public GeoPoint getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return converter.from(rs.getBytes(columnName));
    }

    @Override
    public GeoPoint getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return converter.from(rs.getBytes(columnIndex));
    }

    @Override
    public GeoPoint getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return converter.from(cs.getBytes(columnIndex));
    }
}

