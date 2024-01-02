package idv.yuge.paopao.sqlite.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import idv.yuge.paopao.sqlite.SQLiteFactory;
import idv.yuge.paopao.sqlite.bean.SettingBean;

public class SettingService {
	Connection conn = SQLiteFactory.getConnection();

	public void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS Setting (" + "Key TEXT PRIMARY KEY," + "Value TEXT)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.execute();
//			FormatPrint.timePrintln("Setting 資料表已成功建立！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean insertInto(SettingBean sb) {

		String sql = "INSERT INTO Setting (Key, Value) VALUES (?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, sb.getKey());
			pstmt.setString(2, sb.getValue());
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate != 0) {
//				FormatPrint.timePrintln(sb.getKey() + "成功寫入資料庫");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateSetting(String key, String Value) {
	    String sql = "UPDATE Setting SET Value = ? WHERE Key = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	        pstmt.setString(1, Value);
	        pstmt.setString(2, key);
	        int executeUpdate = pstmt.executeUpdate();
	        if (executeUpdate != 0) {
//	            FormatPrint.timePrintln(key + "成功更新");
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public SettingBean selectByKey(String key) {
        String sql = "SELECT Key, Value FROM Setting WHERE Key = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    SettingBean resultBean = new SettingBean(resultSet.getString("Key"),resultSet.getString("Value"));
//                    FormatPrint.timePrintln(key + "查詢成功");
                    return resultBean;
                } else {
//                	FormatPrint.timePrintln(key + "沒有結果");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	

}
