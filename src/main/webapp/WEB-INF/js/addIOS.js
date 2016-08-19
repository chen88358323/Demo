/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/addios.js")
 */
//host:port
//conn = new Mongo("localhost:30005");
conn = new Mongo( );


db = conn.getDB("datasource");
print("==============================device_ios_101_clean start==================");
device_ios_101_addIndex("device_ios_101_clean"  );

print("==============================device_ios_101_clean  done==================");

print("==============================qudao_device_ios_101 start==================");

qudao_device_ios_101_addIndex("qudao_device_ios_101"  );
print("==============================qudao_device_ios_101  done==================");


function device_ios_101_addIndex(tbname){
    var res=eval("db."+tbname+".ensureIndex (  { token:1} ,{unique:true, background: true })");
    print("token index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { adcode:1} ,{ background: true })");
    print("adcode index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { div:1} ,{ background: true })");
    print("div index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { flag:1} ,{ background: true })");
    print("flag index res:"+res.ok);
}

function qudao_device_ios_101_addIndex(tbname){
    var res=eval("db."+tbname+".ensureIndex (  { diu:1} ,{unique:true, background: true })");
    print("diu index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { diu2_md5:1} ,{ background: true })");
    print("diu2_md5 index res:"+res.ok);

}



function delIndex(tbname){
    var res=eval("db."+tbname+".dropIndex (  { token:1} ,{unique:true, background: true })");
    print("token index res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { adcode:1} ,{ background: true })");
    print("adcode index res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { div:1} ,{ background: true })");
    print("div index res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { flag:1} ,{ background: true })");
    print("flag index res:"+res.ok);
}