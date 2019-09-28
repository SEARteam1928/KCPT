const low = require('lowdb');
const FileSync = require('lowdb/adapters/FileSync');  // 有多种适配器可选择
const LocalStorage = require('lowdb/adapters/LocalStorage');
const FileAsync = require('lowdb/adapters/FileAsync');
const Memory = require('lowdb/adapters/Memory');

class FBDB {

    /**
     * 构造器
     * @param {String} adapter 适配器名称   
     * @param {*} name 数据库文件名称
     */
    constructor(adapter = "FileSync", name = "db.json") {
        switch (adapter.toUpperCase()) {
            case "FILESYNC":
                this.adapter = new FileSync(name);
                break;
            case "FILEASYNC":
                this.adapter = new FileAsync(name);
                break;
            case "LOCALSTORAGE":
                this.adapter = new LocalStorage(name);
                break;
            case "MEMORY":
                this.adapter = new Memory();
                break;
            default:
                this.adapter = new FileSync(this.name);
        }

        this.db = low(this.adapter);
    }

    // defaults(){}

    // push(){}

    // read(){}

    // write(){}

    // cloneDeep(){}
    
    // set(){}

    // unset(){}

    // get(){}

    // remove(){}

    // has(){}

    // filter(){}

    // sortBy(){}

    // take(){}

    // value(){}

    // map(){}

    // size(){}

    // assign(){}


    // getState(){}

    // setState(state){}

}

module.exports = FBDB;