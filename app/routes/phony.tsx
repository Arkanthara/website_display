import { useEffect, useState } from "react";
import { Table } from "rsuite";
import { filter } from "../components/filter";
import * as Utils from "../components/utils";
import { subDays } from "date-fns";
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

interface DataPhony {
  serialNumber: String;
  time: Date;
  column1: number;
  column2: string;
  column3: string;
}


export default function Phony() {
  const [data, setData] = useState<DataPhony[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<any>(null);
  const [value, setValue] = useState<[Date, Date]>([subDays(new Date(), 7), new Date()]);
  const [serialNumberFilter, setSerialNumberFilter] = useState<string>("");
  const [checkbox, setCheckbox] = useState<string[]>(['serialNumber', 'time', 'column1', 'column2', 'column3']);
  const [checkboxChart, setCheckboxChart] = useState<string[]>([]);
  const [sortColumn, setSortColumn] = useState<string>("time");
  const [sortType, setSortType] = useState<"asc" | "desc">("asc");
  const field_names = [{ key: "serialNumber", label: "Serial Number", type: "String" },{ key: "time", label: "Time", type: "Date" },{ key: "column1", label: "Column 1", type: "number" },{ key: "column2", label: "Column 2", type: "string" },{ key: "column3", label: "Column 3", type: "string" }];

  const handleSortColumn = (column: string, type: 'asc' | 'desc' | undefined) => {
    if (!type) return;
    const key = column as keyof DataPhony;
    setSortColumn(column);
    setSortType(type);
    setLoading(true);
    setTimeout(() => {
      const sortedData = [...data].sort((a, b) =>
        (a[key] < b[key] ? -1 : 1) * (type === 'asc' ? 1 : -1)
      );
      setData(sortedData);
      setLoading(false);
    }, 300);
  };

  useEffect(() => {
    Utils.fetchData(`http://localhost:8080/api/phony`, setData, setError, setLoading)
  }, []);

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Phony</h1>
      {loading && <div>Loading Page...</div>}
      {error && <div role="alert">Error: {error}</div>}

      {filter(
        `http://localhost:8080/api/phony`,
        value,
        setValue,
        data,
        setData,
        loading,
        setLoading,
        error,
        setError,
        serialNumberFilter,
        setSerialNumberFilter
      )}

      {Utils.checkboxGroup(field_names, "Columns", checkbox, setCheckbox)}

      <Table
        data={data}
        autoHeight
        loading={loading}
        sortColumn={sortColumn}
        sortType={sortType}
        onSortColumn={handleSortColumn}
      >
        {Utils.renderTableColumns(field_names, checkbox)}
      </Table>

      {Utils.checkboxGroup(field_names, "Chart Columns", checkboxChart, setCheckboxChart, true)}

      {Utils.renderBarCharts(data, checkboxChart)}
    </div>
  );
}
