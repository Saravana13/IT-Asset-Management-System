import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserForm = ({ onUserAdded, editingUser }) => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    role: 'EMPLOYEE'
  });

  useEffect(() => {
    if (editingUser) {
      setFormData(editingUser);
    }
  }, [editingUser]);

  const handleChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingUser) {
        // PUT for update
        await axios.put(`http://localhost:8080/api/users/${editingUser.id}`, formData);
        alert('User updated');
      } else {
        // POST for new user
        await axios.post('http://localhost:8080/api/users', formData);
        alert('User added');
      }
      onUserAdded();
      setFormData({ username: '', email: '', password: '', role: 'EMPLOYEE' });
    } catch (error) {
      console.error('Error saving user', error);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: '20px' }}>
      <input name="username" value={formData.username} onChange={handleChange} placeholder="Username" required />
      <input name="email" value={formData.email} onChange={handleChange} placeholder="Email" required />
      {!editingUser && (
        <input name="password" type="password" value={formData.password} onChange={handleChange} placeholder="Password" required />
      )}
      <select name="role" value={formData.role} onChange={handleChange}>
  <option value="EMPLOYEE">Employee</option>
  <option value="MANAGER">Manager</option>
  <option value="ADMIN">Admin</option>
</select>
      <button type="submit">{editingUser ? 'Update User' : 'Add User'}</button>
    </form>
  );
};

export default UserForm;