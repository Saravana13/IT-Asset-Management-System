import React, { useState } from 'react';
import AssetForm from './components/AssetForm';
import AssetList from './components/AssetList';
import UserForm from './components/UserForm';
import UserList from './components/UserList';

function App() {
  const [activeTab, setActiveTab] = useState('assets');
  const [assetRefreshToggle, setAssetRefreshToggle] = useState(false);
  const [userRefreshToggle, setUserRefreshToggle] = useState(false);

  const handleAssetAdded = () => setAssetRefreshToggle(!assetRefreshToggle);
  const handleUserAdded = () => setUserRefreshToggle(!userRefreshToggle);

  return (
    <div className="App" style={{ padding: '20px' }}>
      <h1>IT Asset Management System</h1>

      {/* Simple Navigation */}
      <div style={{ marginBottom: '20px' }}>
        <button onClick={() => setActiveTab('assets')}>Assets</button>
        <button onClick={() => setActiveTab('users')}>Users</button>
      </div>

      {/* Conditional Rendering Based on Tab */}
      {activeTab === 'assets' && (
        <>
          <AssetForm onAssetAdded={handleAssetAdded} />
          <AssetList newAssetTrigger={assetRefreshToggle} />
        </>
      )}

      {activeTab === 'users' && (
        <>
          <UserForm onUserAdded={handleUserAdded} />
          <UserList newUserTrigger={userRefreshToggle} />
        </>
      )}
    </div>
  );
}

export default App;

