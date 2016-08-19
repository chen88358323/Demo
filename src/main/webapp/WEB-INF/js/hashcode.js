/**
 * Created by chichen.cc on 2015/11/3.
 */

var token="862845025947644-6b95f4e543c7ffffffe8";
var res;
//	res=hashCode(token);
res=getShard(token);
alert('862845025947644-6b95f4e543c7ffffffe8  hash is :'+res);
token="VCtEXqN3/p4BAPGTwCif6pwd";
//	res=hashCode(token);
res=getShard(token);
alert('VCtEXqN3/p4BAPGTwCif6pwd  hash is :'+res);
function isNull(str){
    return str == null || str.value == "";
}

/**
 * 得到分片号
 * @param strKey
 * @return intValue
 */
function getShard(strKey){
    var shardNum=8;
    var index=hashCode(strKey)%shardNum;
    if(index<0)
        index+=shardNum;
    return index;
}
/**
 * java String hashCode 的实现
 * @param strKey
 * @return intValue
 */
function hashCode(strKey)
{
    var hash = 0;
    if(!isNull(strKey))
    {
        for (var i = 0; i < strKey.length; i++)
        {
            hash = hash * 31 + strKey.charCodeAt(i);
            hash = intValue(hash);
        }
    }
    return hash;
}

/**
 * 将js页面的number类型转换为java的int类型
 * @param num
 * @return intValue
 */
function intValue(num)
{
    var MAX_VALUE = 0x7fffffff;
    var MIN_VALUE = -0x80000000;
    if(num > MAX_VALUE || num < MIN_VALUE)
    {
        return num &= 0xFFFFFFFF;
    }
    return num;
}