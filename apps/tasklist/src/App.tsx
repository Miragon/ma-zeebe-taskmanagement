import "./App.css";
import { ReactNode, SyntheticEvent, useEffect, useRef, useState } from "react";
import { Box, Tab, Tabs } from "@mui/material";
import TaskList from "./components/task/TaskList.tsx";
import ProcessList from "./components/process/ProcessList.tsx";
import { Configuration, LoadMetadataControllerApi } from "./client/generated/taskmanager";
import { BASE_URL, setProcessApplications } from "./config.ts";

interface TabPanelProps {
    children?: ReactNode;
    index: number;
    value: number;
}

function CustomTabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;
    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`tabpanel-${index}`}
            aria-labelledby={`tab-${index}`}
            {...other}
        >
            {value === index && <Box p={3}>{children}</Box>}
        </div>
    );
}

function allProps(index: number) {
    return {
        id: `tab-${index}`,
        "aria-controls": `tabpanel-${index}`,
    };
}

function App() {
    const [tabValue, setTabValue] = useState<number>(1);
    const initialized = useRef(false);

    const handleTabChange = (_event: SyntheticEvent, newValue: number) => {
        setTabValue(newValue);
    };

    useEffect(() => {
        async function fetchProcessMetadata() {
            const config = new Configuration({ basePath: `${BASE_URL}/taskmanager` });
            const api = new LoadMetadataControllerApi(config);
            const response = await api.loadMetadata();
            setProcessApplications(response.data.processApplications);
        }

        if (!initialized.current) {
            initialized.current = true;

            fetchProcessMetadata().catch((error) =>
                console.error("Failed to load process definitions:", error),
            );
        }
    }, [initialized]);

    return (
        <>
            <header>
                <h1>Tasklist</h1>
            </header>
            <main>
                <Box>
                    <Tabs value={tabValue} onChange={handleTabChange}>
                        <Tab label="Process" {...allProps(0)} />
                        <Tab label="Tasks" {...allProps(1)} />
                    </Tabs>
                </Box>
                <CustomTabPanel index={0} value={tabValue}>
                    <ProcessList />
                </CustomTabPanel>
                <CustomTabPanel index={1} value={tabValue}>
                    <TaskList />
                </CustomTabPanel>
            </main>
        </>
    );
}

export default App;
