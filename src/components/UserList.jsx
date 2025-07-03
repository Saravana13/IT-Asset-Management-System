import React, { useEffect, useState } from 'react';
import axios from 'axios';
import UserForm from './UserForm';

const UserList = ({ newUserTrigger }) => {
  const [users, setUsers] = useState([]);
  const [filter, setFilter] = useState('');
  const [editingUser, setEditingUser] = useState(null);

  const fetchUsers = () => {
    axios.get('http://localhost:8080/api/users')
      .then(res => setUsers(res.data))
      .catch(err => console.error('Error loading users', err));
  };

  useEffect(() => {
    fetchUsers();
  }, [newUserTrigger]);

  const handleDelete = (id) => {
    if (window.confirm('Are you sure you want to delete this user?')) {
      axios.delete(`http://localhost:8080/api/users/${id}`)
        .then(() => fetchUsers());
    }
  };

  const filteredUsers = users.filter(u =>
    u.username.toLowerCase().includes(filter.toLowerCase()) ||
    u.email.toLowerCase().includes(filter.toLowerCase()) ||
    u.role.toLowerCase().includes(filter.toLowerCase())
  );

  return (
    <div>
      <h2>All Users</h2>

      <input
        type="text"
        placeholder="Filter by name, email or role"
        value={filter}
        onChange={e => setFilter(e.target.value)}
        style={{ marginBottom: '10px' }}
      />

      {editingUser && (
        <UserForm
          editingUser={editingUser}
          onUserAdded={() => {
            setEditingUser(null);
            fetchUsers();
          }}
        />
      )}

      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>ID</th><th>Username</th><th>Email</th><th>Role</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.map(user => (
            <tr key={user.id}>
              <td>{user.id}</td><td>{user.username}</td><td>{user.email}</td><td>{user.role}</td>
              <td>
                <button onClick={() => setEditingUser(user)}>Edit</button>
                <button onClick={() => handleDelete(user.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UserList;