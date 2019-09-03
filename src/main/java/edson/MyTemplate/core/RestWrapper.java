package edson.MyTemplate.core;

import edson.MyTemplate.enums.RestMsg;

/**
 * 返回请求结果的包装类
 */
public class RestWrapper {

    public static RestVO success(Object data){
        RestVO resultVO=new RestVO();
        resultVO.setCode(RestMsg.SUCCESS.getCode());
        resultVO.setMsg(RestMsg.SUCCESS.getMsg());
        resultVO.setData(data);

        return resultVO;
    }

    public static RestVO success(){
        RestVO resultVO=new RestVO();
        resultVO.setCode(RestMsg.SUCCESS.getCode());
        resultVO.setMsg(RestMsg.SUCCESS.getMsg());
        return resultVO;
    }

    public static RestVO error(){
        RestVO resultVO=new RestVO();
        resultVO.setCode(RestMsg.FAILURE.getCode());
        resultVO.setMsg(RestMsg.FAILURE.getMsg());
        return resultVO;
    }

}
