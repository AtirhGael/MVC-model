const express = require('express');
const cores = require('cors')
const app = express()
const pool = require('./db')
const bodyParser = require('body-parser');

app.use(express.json())
app.use(cores())
app.use(bodyParser.json());



app.post('/', async(req,res)=>{
    try {
        const {name,email,phone} = req.body
        const newUser = await pool.query('INSERT INTO employee (name,email,phone) VALUES($1,$2,$3)',[
            name,email,phone
        ])
       
        res.status(200).send(newUser)
        console.log(newUser);
    } catch (error) {
        res.status(401).send('server error ')
        console.log(error);
    }
})

app.get('/',async(req,res)=>{
    try {
        const response = await pool.query('SELECT * FROM employee')
        res.status(200).send(response.rows)
    } catch (error) {
        res.status(401).send('server error ')
        console.log(error);
    }
})

app.listen(5000,console.log('Connection succcesfull app is running on port 5k'))