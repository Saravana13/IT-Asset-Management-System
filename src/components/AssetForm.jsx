import React, { useState, useEffect } from 'react';
import { addAsset, updateAsset } from '../api/assetApi';

const AssetForm = ({ onAssetSaved, assetToEdit }) => {
  const [form, setForm] = useState({
    id: null,
    assetName: '',
    serialNumber: '',
    manufacturer: '',
    model: '',
    assetType: '',
    purchaseDate: '',
    warrantyExpireDate: '',
    status: ''
  });

  useEffect(() => {
    if (assetToEdit) {
      setForm(assetToEdit);
    }
  }, [assetToEdit]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (form.id) {
        await updateAsset(form.id, form);
      } else {
        await addAsset(form);
      }
      setForm({
        id: null,
        assetName: '',
        serialNumber: '',
        manufacturer: '',
        model: '',
        assetType: '',
        purchaseDate: '',
        warrantyExpireDate: '',
        status: ''
      });
      onAssetSaved();
    } catch (err) {
      console.error('Error saving asset:', err);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>{form.id ? 'Update Asset' : 'Add Asset'}</h3>
      <input name="assetName" placeholder="Asset Name" value={form.assetName} onChange={handleChange} required />
      <input name="serialNumber" placeholder="Serial Number" value={form.serialNumber} onChange={handleChange} required />
      <input name="manufacturer" placeholder="Manufacturer" value={form.manufacturer} onChange={handleChange} />
      <input name="model" placeholder="Model" value={form.model} onChange={handleChange} />
      <select name="assetType" value={form.assetType} onChange={handleChange}>
  <option value="">Select Asset Type</option>
  <option value="LAPTOP">Laptop</option>
  <option value="DESKTOP">Desktop</option>
  <option value="PHONE">Phone</option>
</select>
      <input type="date" name="purchaseDate" value={form.purchaseDate} onChange={handleChange} />
      <input type="date" name="warrantyExpireDate" value={form.warrantyExpireDate} onChange={handleChange} />
      <select name="status" value={form.status} onChange={handleChange}>
  <option value="">Select Status</option>
  <option value="AVAILABLE">Available</option>
  <option value="ASSIGNED">Assigned</option>
  <option value="RETIRED">Retired</option>
</select>
      <button type="submit">{form.id ? 'Update' : 'Add'} Asset</button>
    </form>
  );
};

export default AssetForm;