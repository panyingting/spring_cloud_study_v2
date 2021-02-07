package com.pyt.grpc.cliemt;

/**
 * @author : Pan Yingting
 * @date : 2021/1/10 5:41 下午
 */
public interface Functional<Arg,Result>
{
    Result run(Arg arg);
}
