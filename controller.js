const express = require('express');
const router = express.Router();
const employeeModel = require('./model');

router.get('/', async (req, res) => {
  const employees = await employeeModel.getAllEmployees();
  res.render('employee', { employees });
});

router.post('/', async (req, res) => {
  const employee = await employeeModel.addEmployee(req.body);
  res.redirect('/employee');
});

module.exports = router;