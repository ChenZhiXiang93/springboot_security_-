package czx.wt.service.impl;

import czx.wt.mapper.LoginLogInfoMapper;
import czx.wt.pojo.LoginLogInfo;
import czx.wt.service.LoginLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 21:31 2018/8/28
 * @Modified By:
 */
@Service
public class LoginLogServiceImpl implements LoginLogInfoService {

    @Autowired
    private LoginLogInfoMapper loginLogInfoMapper;

    @Override
    public void saveLoginLogInfo(LoginLogInfo loginLogInfo) {
        loginLogInfoMapper.saveLoginLogInfo(loginLogInfo);
    }
}
