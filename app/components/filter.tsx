import {
  Button,
  Input,
  DateRangePicker,
  TimeRangePicker,
  HStack,
  VStack,
  Stack,
} from "rsuite";
import React from "react";
import * as Utils from "./utils";


export function filter(
    url: string,
    value: [Date, Date],
    setValue: React.Dispatch<React.SetStateAction<[Date, Date]>>,
    data: any[],
    setData: (data: any[]) => void,
    loading: boolean,
    setLoading: (loading: boolean) => void,
    error: any,
    setError: (error: any) => void,
    serialNumberFilter: string,
    setSerialNumberFilter: (serialNumberFilter: string) => void,
  ) {
  return (
    <VStack spacing={20}>
      <HStack spacing={20}>
        <Stack.Item grow={1}>
          <DateRangePicker
            value={value}
            onChange={(newvalue) => (newvalue ? setValue(newvalue) : null)}
          />
        </Stack.Item>
        <Stack.Item grow={1}>
          <TimeRangePicker
            value={value}
            onChange={(newvalue) => (newvalue ? setValue(newvalue) : null)}
            format={"HH:mm:ss"}
          />
        </Stack.Item>

        <Stack.Item grow={1}>
          <Button
            onClick={() =>
              Utils.fetchByDateRange(url, value, setData, setError, setLoading)
            }
          >
            Search by Time Range
          </Button>
        </Stack.Item>
      </HStack>
      <HStack spacing={20}>
        <Stack.Item grow={1}>
          <Input
            value={serialNumberFilter}
            onChange={(e) => setSerialNumberFilter(e.replace(/\+/g, ""))}
            placeholder="Search by Serial Number"
          />
        </Stack.Item>
        <Stack.Item grow={1}>
          <Button
            onClick={() =>
              Utils.fetchBySerialNumber(
                url,
                serialNumberFilter,
                setData,
                setError,
                setLoading
              )
            }
          >
            Search by Serial Number
          </Button>
        </Stack.Item>
      </HStack>{" "}
      <Button
        appearance="primary"
        onClick={() => Utils.fetchData(url, setData, setError, setLoading)}
      >
        Reset
      </Button>
    </VStack>
  );
}
