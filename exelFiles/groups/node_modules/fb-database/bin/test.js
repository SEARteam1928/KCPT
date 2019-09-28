const FBDB = require("../src/index");

let filedb = (new FBDB("FileSync", "filedb.json")).db;

//let filedb_async = new FBDB("FileAsync", "filedb_async.json");

// let lsdb = new FBDB("LocalStorage", "lsdb");

//let mdb = new FBDB("Memory");

filedb.defaults({
    watchlist: []
}).write();

let watchlist = filedb.get('watchlist');
console.log()
watchlist
    .push({ id: 1, title: 'lowdb is awesome' })
    .write();