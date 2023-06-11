const Pool = require('pg').Pool;

const pool = new Pool({
    user:'postgres',
    password:'UBa19P0170',
    host:'localhost',
    port:5432,
    database:'pern',
})

module.exports = pool;