const getAllEmployees = async () => {
    const res = await client.query('SELECT * FROM employees');
    return res.rows;
  };
  
  const addEmployee = async (employee) => {
    const { name, email, phone } = employee;
    const res = await client.query(
      `INSERT INTO employees (name, email, phone) VALUES ('${name}', '${email}', '${phone}') RETURNING *`
    );
    return res.rows[0];
  };
  
  module.exports = {
    getAllEmployees,
    addEmployee,
  };