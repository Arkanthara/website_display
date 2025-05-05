import { format } from "date-fns";
import { Checkbox, CheckboxGroup, Table } from "rsuite";
import { Bar } from "react-chartjs-2";
import { generateChartData, generateChartOptions } from "./chartUtils";

export async function fetchData(
  url: string,
  setData: (data: any[]) => void,
  setError: (error: string) => void,
  setLoading: (loading: boolean) => void
) {
  setLoading(true);
  try {
    const response = await fetch(url);
    if (!response.ok) {
      setError(`Failed to fetch data: ${response.status}`);
    } else {
      const json = await response.json();
      const formatted = json.map((item: any) => ({
        ...item,
        time: format(new Date(item.time), "dd-MM HH:mm"),
      }));
      setData(formatted);
    }
  } catch (err) {
    setError(err instanceof Error ? err.message : "Unknown error");
  } finally {
    setLoading(false);
  }
}

export async function fetchByDateRange(
  baseUrl: string,
  value: [Date, Date],
  setData: (data: any[]) => void,
  setError: (error: string) => void,
  setLoading: (loading: boolean) => void
) {
  setLoading(true);
  try {
    const url = new URL(baseUrl + "/findByTimeBetween");
    url.searchParams.append("start", value[0].toISOString().split(".")[0]);
    url.searchParams.append("end", value[1].toISOString().split(".")[0]);
    const response = await fetch(url.toString());
    if (!response.ok) {
      setError(`Failed to fetch by time range: ${response.status}`);
    } else {
      const json = await response.json();
      const formatted = json.map((item: any) => ({
        ...item,
        time: format(new Date(item.time), "dd-MM HH:mm"),
      }));
      setData(formatted);
    }
  } catch (err) {
    setError(err instanceof Error ? err.message : "Unknown error");
  } finally {
    setLoading(false);
  }
}

export async function fetchBySerialNumber(
  baseUrl: string,
  serialNumber: string,
  setData: (data: any[]) => void,
  setError: (error: string) => void,
  setLoading: (loading: boolean) => void
) {
  setLoading(true);
  try {
    const url = new URL(baseUrl + "/findBySerialNumber");
    url.searchParams.append("serialNumber", serialNumber);
    const response = await fetch(url.toString());
    if (!response.ok) {
      setError(`Failed to fetch by serial number: ${response.status}`);
    } else {
      const json = await response.json();
      const formatted = json.map((item: any) => ({
        ...item,
        time: format(new Date(item.time), "dd-MM HH:mm"),
      }));
      setData(formatted);
    }
  } catch (err) {
    setError(err instanceof Error ? err.message : "Unknown error");
  } finally {
    setLoading(false);
  }
}

export function renderTableColumns(fields: { key: string; label: string; type: string; }[], checkbox: string[]) {
  return fields
    .filter(item => checkbox.includes(item.key))
    .map((item) => (
      <Table.Column key={item.key} flexGrow={1} sortable>
        <Table.HeaderCell>{item.label}</Table.HeaderCell>
        <Table.Cell dataKey={item.key} />
      </Table.Column>
    ));
}

export function renderBarCharts(data: any[], keys: string[]) {
  return keys.map((key) => (
    <Bar key={key} data={generateChartData(data, key)} options={generateChartOptions(key)} />
  ));
}

export function checkboxGroup(field_names: { key: string; label: string; type: string; }[], name: string, checkbox: string[], setCheckbox: React.Dispatch<React.SetStateAction<string[]>>, onlyNumber: boolean = false) {
  const fields = onlyNumber ? field_names.filter(name => name.type === "number") : field_names;
  return (
      <CheckboxGroup inline name={name} value={checkbox} onChange={(c) => setCheckbox(Array.isArray(c) ? c.map(String) : [])}>
        {fields.map((name) => <Checkbox key={name.key} value={name.key}>{name.label}</Checkbox>)}
      </CheckboxGroup>);
};
