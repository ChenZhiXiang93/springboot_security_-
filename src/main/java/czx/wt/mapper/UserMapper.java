package czx.wt.mapper;

import czx.wt.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:42 2018/8/24
 * @Modified By:
 */
public interface UserMapper {

    User getUserByName(@Param("username") String username);

    void updateUser(User user);

    void updateLastTimeByErrorCount(String lastModifyTime);

}
