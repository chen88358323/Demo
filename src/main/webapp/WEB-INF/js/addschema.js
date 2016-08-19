/**
 * Created by chichen.cc on 2015/12/16.
 *
 * under mongo cmd #load("/home/chichen.cc/code/addschema.js")
 *
 * (mongo console)
 * 1.del weburl schema
 * (mongo shell)
 * 2.edit top schema
 * 3.add weburl schema
 * 4.add nearbyschema
 *
 *
 **/
//host:port
//conn = new Mongo("localhost:30005");
conn = new Mongo( "localhost:31111");
db = conn.getDB("PNSManage");
db.auth("pushmongodb","push");

print("==============================del==================================");
//addschemaURL()
print("==============================update==================");
updateSchemaOrder();
print("==============================addschemaNearBy start==================");
addschemaNearBy();
print("==============================addschemaNearBy  done==================");
print("==============================addschemaURL() start==================");
addschemaURL();
print("==============================addschemaURL()  done==================");
function getOldData(){
   var sql="db.appschema.find({appId:101,schemaId:117})";
    var res=eval(sql);
    print("schemaid 118 objId is "+res);
    print("schemaid 118 objId is "+res._id);
}
    //online test same
    //db.appschema.remove({'_id' : ObjectId('55a65aebbc19fd1dc00c43bf')});//117
    //db.appschema.remove({'_id' : ObjectId('55a8c2f8bc19fd2614c4db52')});//17

//add weburl schema
function addschemaURL(){
    var sql="db.appschema.save({'_id' : ObjectId('55a8c2f8bc19fd2614c4db52'),\"appId\" : 101,\"platform\" : \"ios\",\"name\" : " +
        "\"打开外部URL(使用amap打开)\",\"schema\" : " +
        "\"iosamap://openFeature?featureName=OpenURL&urlType=" +
        "0&contentType=autonavi&websiteName=amap\",\"schemaId\" :" +
        " 1,\"params\" : {\"url\" : {\"name\" : \"url\",\"nullable\"" +
        " : false,\"order\" : 1  }} })";
    var res=eval(sql);
    print(sql+" res:"+res);
    sql="db.appschema.save({'_id' : ObjectId('55a65aebbc19fd1dc00c43bf'),\"appId\" : 101," +
    "\"platform\" : \"android\",\"name\" : " +
    "\"打开外部URL (使用amap打开)( 版本V6.0.1起)\",\"schema\" : " +
    "\"androidamap://openFeature?featureName=OpenURL&urlType=0&" +
    "contentType=autonavi&websiteName=amap\",\"schemaId\" : 100," +
    "\"params\" : {\"url\" : {\"name\" : \"url\",\"nullable\" : " +
    "false,\"order\" : 1  }} })";
    res=eval(sql);
    print(sql+" res:"+res);
}

function updateSchemaOrder(){
    var sql="db.appschema.update({appId:101,'_id' : ObjectId('55a656c3bc19fd1dc00c43b0'),'schemaId' : 100 }, { $set : { 'schemaId' : 117} } )";
    var res=eval(sql);
    print(sql+" res:"+res);

    sql="db.appschema.update({appId:101,'_id' : ObjectId('55a74548bc19fd2614c4db3c'),'schemaId' : 1 }, { $set : { 'schemaId' : 17} } )";
    res=eval(sql);
    print(sql+" res:"+res);

}

function addschemaNearBy(){
    var sql="db.appschema.save({'_id' : ObjectId('5671401c4e60a6450a3baf36'),'appId' : 101,'name' : '打开附近页面','params' : {'lat' : {'name' : '纬度',"+
               " 'nullable' : true,'order' : 1},'lon' : {'name' : '经度','nullable' : true,'order' : 2},"+
        "'poiname' : {'name' : 'Poi名称','nullable' : true,'order' : 3},'poiid' : {'name' : 'Poiid','nullable' : true,"+
        "'order' : 4}},'platform' : 'android','schema' : "+
        "'androidamap://openFeature?featureName=Nearby&page=main&from=push','schemaId' : 119})";
    var res=eval(sql);
    print(sql+" res:"+res);
    sql="db.appschema.save({'_id' : ObjectId('5671401c4e60a6450a3baf37'),'appId' : 101,'name' : '打开附近页面','params' : {'lat' : {"+
    "'name' : '纬度','nullable' : true,'order' : 1},'lon' : {'name' : '经度','nullable' : true,"+
    "'order' : 2},'poiname' : {'name' : 'Poi名称','nullable' : true,'order' : 3"+
    "  }, 'poiid' : {'name' : 'Poiid','nullable' : true,'order' : 4}},'platform' : 'ios','schema' : 'iosamap://openFeature?featureName=Nearby&page=main&from=push',"+
    "'schemaId' : 18})";
    res=eval(sql);
    print(sql+" res:"+res);
}
