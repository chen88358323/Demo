/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/mongo.js")
 */
conn = new Mongo("localhost:30009");
//conn = new Mongo( );
db = conn.getDB("dbname");

delIndex();

var res=db.collectionname.ensureIndex (  { ttttt:1} ,{ background: true });
print("ttttt index res:"+res.ok);

addIndex("ttttt");

function delIndex(){
    db.collectionname.dropIndex (  { ttttt:1} ,{ background: true });
}
function addIndex(dataname){
    var res=eval("db.collectionname.ensureIndex (  { "+dataname+":1} ,{ background: true })");
    print(dataname+" index res:"+res.ok);
}