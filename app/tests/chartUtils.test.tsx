import { describe, it, expect } from "vitest";
import { generateChartData, generateChartOptions } from "../components/chartUtils";

describe("chartUtils", () => {
    it("should generate chart options", () => {
        const options = generateChartOptions("test");
        expect(options.plugins?.title?.text).toBe("test");
    });
    
    it("should generate chart data", () => {
        const dummyData = [
        { serialNumber: "123", time: new Date().toISOString(), init: 5 },
        { serialNumber: "456", time: new Date().toISOString(), init: 10 },
        ];
        const colorFn = () => ({ backgroundColor: "rgba(0,0,0,0.7)", borderColor: "rgba(0,0,0,1)" });
        const chartData = generateChartData(dummyData, "init", colorFn);
        expect(chartData.datasets.length).toBe(2);
    });
});
