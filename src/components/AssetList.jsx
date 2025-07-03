import React, { useEffect, useState } from 'react';
import { getAssets, deleteAsset } from '../api/assetApi';

const AssetList = ({ onEdit }) => {
  const [assets, setAssets] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [filters, setFilters] = useState({
    name: '',
    type: '',
    status: ''
  });

  const loadAssets = async () => {
    const res = await getAssets();
    setAssets(res.data);
    setFiltered(res.data);
  };

  useEffect(() => {
    loadAssets();
  }, []);

  useEffect(() => {
    let filteredData = assets;
    if (filters.name)
      filteredData = filteredData.filter(a => a.assetName.toLowerCase().includes(filters.name.toLowerCase()));
    if (filters.type)
      filteredData = filteredData.filter(a => a.assetType.toLowerCase().includes(filters.type.toLowerCase()));
    if (filters.status)
      filteredData = filteredData.filter(a => a.status.toLowerCase().includes(filters.status.toLowerCase()));
    setFiltered(filteredData);
  }, [filters, assets]);

  const handleDelete = async (id) => {
    await deleteAsset(id);
    loadAssets();
  };

  return (
    <div>
      <h2>Asset List</h2>

      <div style={{ marginBottom: '10px' }}>
        <input
          placeholder="Search by name"
          value={filters.name}
          onChange={e => setFilters({ ...filters, name: e.target.value })}
        />
        <input
          placeholder="Filter by type"
          value={filters.type}
          onChange={e => setFilters({ ...filters, type: e.target.value })}
        />
        <input
          placeholder="Filter by status"
          value={filters.status}
          onChange={e => setFilters({ ...filters, status: e.target.value })}
        />
      </div>

      <ul>
        {filtered.map(asset => (
          <li key={asset.id}>
            {asset.assetName} - {asset.assetType} - {asset.status}
            <button onClick={() => onEdit(asset)}>Edit</button>
            <button onClick={() => handleDelete(asset.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AssetList;